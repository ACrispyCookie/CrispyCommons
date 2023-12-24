package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleScoreboardLine extends AbstractScoreboardLine {

    public SimpleScoreboardLine(String staticLine, Collection<? extends Player> receivers) {
        super(new SimpleTextElement(staticLine), receivers);
    }

    public SimpleScoreboardLine(Collection<? extends String> frames, int period, Collection<? extends Player> receivers) {
        super(null, receivers);
        this.element = new TextElement(frames, period) {
            @Override
            protected void update() {
                SimpleScoreboardLine.this.update();
            }
        };
    }

    @Override
    protected void show(Scoreboard bukkitScoreboard) {
        int index = scoreboard.getCurrentContent().indexOf(this);
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");

        String line = ChatColor.translateAlternateColorCodes('&', getCurrentContent());
        ArrayList<String> strings = getStrings(line, index, bukkitScoreboard);
        String prefix = strings.get(0);
        String suffix = strings.get(1);
        String teamEntry = strings.get(2);
        Team team = bukkitScoreboard.registerNewTeam(String.valueOf(index));
        team.addEntry(teamEntry);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        obj.getScore(teamEntry).setScore(15 - index);
    }

    @Override
    protected void hide(Scoreboard bukkitScoreboard) {
        int index = scoreboard.getCurrentContent().indexOf(this);

        bukkitScoreboard.getTeam(String.valueOf(index)).unregister();
    }

    @Override
    protected void update(Scoreboard bukkitScoreboard) {
        int index = getScoreboard().getCurrentContent().indexOf(this);

        String lineString = ChatColor.translateAlternateColorCodes('&', getCurrentContent());
        ArrayList<String> strings = getStrings(lineString, index, bukkitScoreboard);
        String prefix = strings.get(0);
        String suffix = strings.get(1);
        Team team = bukkitScoreboard.getTeam(String.valueOf(index));
        team.setPrefix(prefix);
        team.setSuffix(suffix);
    }



    public ArrayList<String> getStrings(String line, int i, Scoreboard b){
        ArrayList<String> list = new ArrayList<>();
        String prefix;
        String suffix;
        String teamEntry;
        if(line.length() >= 32 && line.charAt(15) == ChatColor.COLOR_CHAR){
            ArrayList<Integer> startEnd = legthOfColor(line, 15);
            int startOfCode = startEnd.get(0);
            int endOfCode = startEnd.get(1);
            prefix = line.substring(0, startOfCode);
            suffix = line.substring(endOfCode, 32);
            teamEntry = line.substring(startOfCode, endOfCode);
        }
        else if(line.length() > 16 && line.length() < 32 && line.charAt(15) == ChatColor.COLOR_CHAR){
            ArrayList<Integer> startEnd = legthOfColor(line, 15);
            int startOfCode = startEnd.get(0);
            prefix = line.substring(0, startOfCode);
            suffix = line.substring(startOfCode);
            teamEntry = String.valueOf(ChatColor.values()[i]);
        }
        else{
            prefix = line.length() > 16 ? line.substring(0, 16) : line;
            suffix = line.length() <= 16 ? "" : line.length() > 32 ? line.substring(16, 32) : line.substring(16);
            if(findLastColorCode(prefix) != -1){
                ArrayList<Integer> startEnd = legthOfColor(prefix, findLastColorCode(prefix));
                int startOfCode = startEnd.get(0);
                int endOfCode = startEnd.get(1);
                teamEntry = prefix.substring(startOfCode, endOfCode);
            }
            else{
                teamEntry = ChatColor.WHITE + "";
            }
            for(Team t : b.getTeams()){
                for(String s : t.getEntries()){
                    if(s.equals(teamEntry)){
                        teamEntry = ChatColor.COLOR_CHAR + "r" + teamEntry;
                    }
                }
            }
        }
        list.add(prefix);
        list.add(suffix);
        list.add(teamEntry);
        return list;
    }

    private ArrayList<Integer> legthOfColor(String s, int index){
        if(index < s.length()){
            if(s.charAt(index) == ChatColor.COLOR_CHAR){
                ArrayList<Integer> integers = new ArrayList<>();
                int start = 0;
                int end = 0;
                int increment = 0;
                while(index + increment < s.length() && s.charAt(index + increment) == ChatColor.COLOR_CHAR){
                    increment += 2;
                }
                end = Math.min(increment + index, s.length());
                increment = 0;
                while(index - increment > 0 && s.charAt(index - increment) == ChatColor.COLOR_CHAR){
                    increment += 2;
                }
                start = Math.max(index - increment + 2, 0);
                integers.add(start);
                integers.add(end);
                return integers;
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    private int findLastColorCode(String s){
        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) == ChatColor.COLOR_CHAR){
                return i;
            }
        }
        return -1;
    }
}
