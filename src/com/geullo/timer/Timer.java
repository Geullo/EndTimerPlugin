package com.geullo.timer;

import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {
    public boolean timerVisibled = false;
    public Minute minute = new Minute();
    public Second second = new Second();
    public class Minute{
        protected int minute=0;
        public void add() {
            add(1);
        }
        public void add(int amount){
            if (amount<99 || minute+amount<99) {
                minute += amount;
            }else{
                minute = 99;
            }
        }
        public void remove(int amt){
            if (minute-amt>=0) {
                minute-=amt;
            }else{
                minute=0;
            }
        }
        public void remove(){
            remove(1);
        }
        public void set(int amount){
            if (amount >99) minute = 99;
            else minute = amount;
        }
        public String get() {
            if (minute<=0) {
                return "00";
            }else if (minute<10){
                return "0"+minute;
            }
            return ""+minute;
        }
    }
    public class Second{
        protected int second=0;
        public void add(){
            add(1);
        }
        public void add(int amount){
            if (second+amount>=60){
                minute.add(amount/60);
                second=0;
                second+=amount%60;
            }else{
                second+=amount;
            }
        }
        public void remove(){
            remove(1);
        }
        public void remove(int amt){
            if (second-amt<=0){
                if (amt!=1) {
                    System.out.println(((second-amt)%60) + " /a " + second);
                }
                if (minute.minute>0) {
                    minute.remove();
                    amt = second-amt;
                    second = 60;
                }else {
                    timerVisibled = false;
                    return;
                }
                if (amt!=1) {
                    System.out.println(amt + " /b " + second);
                }
            }
            if (amt>=0) {
                if (second - amt <= 0 || second - amt >= 0) {
                    if (amt!=1) {
                        System.out.println(amt + " /c " + second);
                    }
                    second -= amt;
                    if (amt!=1) {
                        System.out.println(amt + " /d " + second);
                    }
                }
            }else{
                second+=amt;
                if (amt!=1) {
                    System.out.println(amt + " /e " + second);
                }
            }
        }
        public void set(int amount){
            if (amount >99) second = 99;
            second = amount;
        }
        public String get() {
            if (second<=0) {
                return "00";
            }else if (second<10){
                return "0"+second;
            }
            return ""+second;
        }
    }

    @Override
    public void run() {
        if (timerVisibled)
        {
            SendData.sendData("showtimer", Main.getPlugin());
            second.remove();
            SendData.sendData(getTimer(), Main.getPlugin());
            return;
        }
        else SendData.sendData("stoptimer", Main.getPlugin());
    }
    public void setTimerVisibled(boolean visibled){
        timerVisibled = visibled;
    }
    public String getTimer(){
        return minute.get()+":"+second.get();
    }
    public String getTimerKorean(){
        return minute.get()+"분 "+second.get()+"초";
    }

    @Override
    public String toString() {
        return "Timer{"+getTimer()+"}";
    }
}
