package net.entropyentertainment.simplefactions;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import net.entropyentertainment.simplefactions.commands.FactionMenuCommand;
import net.entropyentertainment.simplefactions.database.Database;
import net.entropyentertainment.simplefactions.model.Faction;
import net.entropyentertainment.simplefactions.model.Member;
import org.bouncycastle.asn1.dvcs.Data;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.logging.Level;

public class SimpleFactions extends JavaPlugin {
    public SimpleFactions(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        Database.initialize(this.getDataDirectory());

        this.getCommandRegistry().registerCommand(new FactionMenuCommand());

        //members test
        for (int i = 0; i < 10; i++){
            Database.getInstance().members().insert(new Member(i, "test" + i));
        }

        Member member = Database.getInstance().members().findById(0);
        this.getLogger().at(Level.INFO).log("Member.findById: " + member.getId() + " : " + member.getName() );

        member.setName("HelloWorld");
        Database.getInstance().members().update(member);

        List<Member> members = Database.getInstance().members().findAll();

        Database.getInstance().members().deleteById(members.get(2).getId());

        //Faction Test
        Faction faction = Database.getInstance().factions().insert(new Faction("Test", member.getId(), 0));

        faction.setColor(2);
        Database.getInstance().factions().update(faction);

        member.setFactionId(faction.getId());
        Database.getInstance().members().update(member);

        Database.getInstance().factions().findById(member.getFactionId());

        Database.getInstance().factions().findByOwnerId(member.getId());

        Database.getInstance().factions().findAll();

        Database.getInstance().factions().deleteById(faction.getId());
    }
}
