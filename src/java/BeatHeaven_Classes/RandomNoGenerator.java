/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeatHeaven_Classes;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.String.*;
import java.lang.StringBuffer.*;


public class RandomNoGenerator
{
    public RandomNoGenerator(){

    }
    public long getRandomCode(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        String str=(String)dateFormat.format(date);
        long randomCode;
        randomCode=Long.parseLong(str.substring(0,4))+Long.parseLong(str.substring(5,7))+Long.parseLong(str.substring(8,10))+Long.parseLong(str.substring(11,13))+Long.parseLong(str.substring(14,16))+Long.parseLong(str.substring(17,19));
        return randomCode;
    }
   /* public static void main(String s[]){
        RandomNoGenerator rcg=new RandomNoGenerator();
        System.out.println("Random Code:"+ rcg.getRandomCode());

    }*/
}

