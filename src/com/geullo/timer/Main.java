package com.geullo.timer;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.*;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;
import java.util.logging.*;


public class Main extends JavaPlugin {
    public final static Logger log = Logger.getLogger("Minecraft");
    private static final Timer timer = new Timer();
    private String setMapping = "set,설정",addMapping="add,추가",removeMapping="remove,제거",cmdHourMapping = "시,hour",cmdMinuteMapping = "분,minute",cmdSecondMapping = "초,second";
    private static Main plugin;
    private static final String Header = ChatColor.WHITE+""+ChatColor.BOLD+"[ "+ChatColor.YELLOW+ChatColor.BOLD+"TIMER "+ChatColor.WHITE+""+ChatColor.BOLD+"] "+ChatColor.WHITE;
    public static Main getPlugin(){
        return plugin;
    }

    public void onEnable() {
        plugin = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "gtimer");
        BukkitScheduler scheduler = getServer().getScheduler();

        scheduler.scheduleSyncRepeatingTask(getPlugin(),timer,0,20);


        log("Geullo Timer Plugin Enabled.", Level.INFO);
    }

    public void onDisable() {
        log("Geullo Timer Plugin Disabled.", Level.INFO);
    }
    public static void log(String string, Level level) {
        log.log(level, "[Geullo Timer Plugin]" + string);
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args)
    {
        if ("gtimer".equals(cmd.getName()))
        {
            Player sender = Bukkit.getPlayer(s.getName());
            if (sender!=null)
            {
                if (args.length<1) return true;
                if (cmdMinute(args[0])) {
                    if (args.length < 2) return true;
                    if (cmdAdd(args[1])) {
                        if (args.length < 3) {timer.minute.add();sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+"1분 "+ChatColor.WHITE+"을 추가했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");timer.timerVisibled=true;return true;}
                        if (Integer.parseInt(args[2])<0) {sender.sendMessage(wrongMessage());return true;}
//                        sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+args[2]+"분 "+ChatColor.WHITE+"을 추가했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");
                        timer.minute.add(Integer.parseInt(args[2]));
                        return true;
                    } else if (cmdRemove(args[1])) {
                        if (args.length < 3) {timer.minute.remove();sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+"1분 "+ChatColor.WHITE+"을 제거했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");timer.timerVisibled=true;return true;}
                        if (Integer.parseInt(args[2]) > 60) return true;
                        if (Integer.parseInt(args[2])<0) {sender.sendMessage(wrongMessage());return true;}
//                        sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+args[2]+"분 "+ChatColor.WHITE+"을 제거했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");
                        timer.minute.remove(Integer.parseInt(args[2]));
                        return true;
                    } else if (cmdSet(args[1])) {
                        if (args.length < 3) return true;
                        if (Integer.parseInt(args[2])<0) {sender.sendMessage(wrongMessage());return true;}
                        sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+args[2]+"분 "+ChatColor.WHITE+"을 제거했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");
                        timer.minute.set(Integer.parseInt(args[2]));
                        return true;
                    }
                }else if (cmdSecond(args[0])) {
                    if (args.length < 2) return true;
                    if (cmdAdd(args[1])) {
                        if (args.length < 3) {timer.second.add();sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+"1초 "+ChatColor.WHITE+"를 추가했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");timer.timerVisibled=true;return true;}
                        if (Integer.parseInt(args[2])<0) {sender.sendMessage(wrongMessage());return true;}
                        sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+args[2]+"초 "+ChatColor.WHITE+"를 추가했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");
                        timer.second.add(Integer.parseInt(args[2]));
                        return true;
                    } else if (cmdRemove(args[1])) {
                        if (args.length < 3) {
                            timer.second.remove();
                            sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+"1초 "+ChatColor.WHITE+"를 제거했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");
                            return true;
                        }
                        if (Integer.parseInt(args[2]) > 60) return true;
                        if (Integer.parseInt(args[2])<0) {sender.sendMessage(wrongMessage());return true;}
                        timer.second.remove(Integer.parseInt(args[2]));
                        sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+args[2]+"초 "+ChatColor.WHITE+"를 제거했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");
                        return true;
                    } else if (cmdSet(args[1])) {
                        if (args.length < 3) return true;
                        if (Integer.parseInt(args[2])<0) {sender.sendMessage(wrongMessage());return true;}
                        sender.sendMessage(Header+ChatColor.GOLD+ChatColor.BOLD+args[2]+"초 "+ChatColor.WHITE+"로 설정했습니다."+ChatColor.GRAY+" ( "+timer.getTimerKorean()+" )");
                        timer.second.set(Integer.parseInt(args[2]));
                        return true;
                    }
                }
                else if ("start".equals(args[0]))
                {
                    System.out.println("test");
                    timer.timerVisibled = true;
                    return true;
                }
                else if ("stop".equals(args[0]))
                {
                    timer.timerVisibled = false;
                    return true;
                }
                else if ("get".equals(args[0]))
                {
                    sender.sendMessage(Header+ChatColor.GOLD+timer.getTimerKorean());
                    return true;
                }
                else{
                    if (args.length==2) {
                        int  minute, second;
                        minute = Integer.parseInt(args[0]);
                        second = Integer.parseInt(args[1]);
                        if (minute<100&&second<100) {
                            timer.minute.set(minute);
                            timer.second.set(second);
                        }
                        sender.sendMessage(Header + ChatColor.GOLD + ChatColor.BOLD + timer.getTimerKorean() + ChatColor.WHITE + "로 설정했습니다.");
                    }else {
                        sender.sendMessage(Header+"  /gtimer [minute / second] add [amount]  -  [hour / minute / second]를  [amount]만큼 추가 합니다.");
                        sender.sendMessage(Header+"  /gtimer [minute / second] remove [amount]  -  [hour / minute / second]를  [amount]만큼 제거 합니다.");
                        sender.sendMessage(Header+"  /gtimer [minute / second] add  -  [hour / minute / second]를  1 을 추가 합니다.");
                        sender.sendMessage(Header+"  /gtimer [minute / second] remove  -  [hour / minute / second]를  1 을 제거 합니다.");
                        sender.sendMessage(Header+"  /gtimer start  -  타이머를 실행합니다.");
                        sender.sendMessage(Header+"  /gtimer stop  -  타이머를 멈춥니다.");
                        sender.sendMessage(Header+"  /gtimer get  -  현재 타이머 시간을 확인합니다.");
                        sender.sendMessage(Header+"  /gtimer [minute] [second]  -  타이머를 [hour] [minute] [second] 로 설정합니다.");
                    }
                }
                return true;
            }
            return true;
        }
        return true;
    }
    private boolean cmdSet(String arg){
        String[] s = setMapping.split(",");
        return s[0].equalsIgnoreCase(arg)||s[1].equalsIgnoreCase(arg);
    }
    private boolean cmdAdd(String arg){
        String[] s = addMapping.split(",");
        return s[0].equalsIgnoreCase(arg)||s[1].equalsIgnoreCase(arg);
    }
    private boolean cmdRemove(String arg){
        String[] s = removeMapping.split(",");
        return s[0].equalsIgnoreCase(arg)||s[1].equalsIgnoreCase(arg);
    }
    private boolean cmdHour(String arg){
        String[] s = cmdHourMapping.split(",");
        return s[0].equalsIgnoreCase(arg)||s[1].equalsIgnoreCase(arg);
    }
    private boolean cmdMinute(String arg){
        String[] s = cmdMinuteMapping.split(",");
        return s[0].equalsIgnoreCase(arg)||s[1].equalsIgnoreCase(arg);
    }
    private boolean cmdSecond(String arg){
        String[] s = cmdSecondMapping.split(",");
        return s[0].equalsIgnoreCase(arg)||s[1].equalsIgnoreCase(arg);
    }
    private String wrongMessage(){
        return Header+"0보다 작은수를 입력할수 없습니다.";
    }
}
