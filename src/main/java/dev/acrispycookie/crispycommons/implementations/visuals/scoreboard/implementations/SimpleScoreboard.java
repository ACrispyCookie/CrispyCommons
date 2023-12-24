package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.AbstractCrispyScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class SimpleScoreboard extends AbstractCrispyScoreboard {

//    @Override
//    protected void showInternal() {
//        org.bukkit.scoreboard.ScoreboardManager m = Bukkit.getScoreboardManager();
//        org.bukkit.scoreboard.Scoreboard b = m.getNewScoreboard();
//        Objective o = b.registerNewObjective("[CrispyCommons]", "dummy");
//        o.setDisplaySlot(DisplaySlot.SIDEBAR);
//        o.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
//        int score = 15;
//        for(int i = 0; i < lines.size(); i++){
//            String line = ChatColor.translateAlternateColorCodes('&', lines.get(i).getCurrentContent());
//            ArrayList<String> strings = getStrings(line, i, b);
//            String prefix = strings.get(0);
//            String suffix = strings.get(1);
//            String teamEntry = strings.get(2);
//            Team team = b.registerNewTeam(String.valueOf(i));
//            team.addEntry(teamEntry);
//            team.setPrefix(prefix);
//            team.setSuffix(suffix);
//            o.getScore(teamEntry).setScore(score);
//            score--;
//        }
//        Team team = b.registerNewTeam(("nameTagHide-" + player.getUniqueId().toString()).substring(0, 16));
//        team.setNameTagVisibility(NameTagVisibility.NEVER);
//        player.setScoreboard(b);
//    }
//
//    @Override
//    protected void hideInternal() {
//        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
//    }
//
//    @Override
//    public void update() {
//        if(player.isOnline()) {
//            updateLines();
//            updateTeams();
//        } else {
//            hide();
//        }
//    }
//
//    private void updateLines(){
//        for(int i = 0; i < lines.size(); i++){
//            String lineString = ChatColor.translateAlternateColorCodes('&', lines.get(0).getCurrentContent());
//            ArrayList<String> strings = getStrings(lineString, i, player.getScoreboard());
//            String prefix = strings.get(0);
//            String suffix = strings.get(1);
//            Team team = player.getScoreboard().getTeam(String.valueOf(i));
//            team.setPrefix(prefix);
//            team.setSuffix(suffix);
//        }
//    }
//
//    private void updateTeams(){
//        for(Player pl : Bukkit.getOnlinePlayers()){
//            player.getScoreboard().getTeam(("nameTagHide-" + player.getUniqueId().toString()).substring(0, 16)).addPlayer(pl);
//        }
//    }
}
