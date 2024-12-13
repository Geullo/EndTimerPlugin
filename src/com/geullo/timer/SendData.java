package com.geullo.timer;

import com.google.common.io.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SendData {
    public static void sendData(String sendID, Plugin plugin){
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendData(sendID,player,plugin);
        }
    }
    public static void sendData(String sendID, Player player, Plugin plugin){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(new StringBuilder(sendID).toString());
//        System.out.println(sendID);
        player.sendPluginMessage(plugin, "gtimer", out.toByteArray());
    }

}
