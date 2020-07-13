package com.njau.commen;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.njau.dao.PigDeliveryFlagDao;
import com.njau.dao.Pre_alertDao;

public class SendPreAlert implements Runnable{

	public static void main1() {
		
		int countsum = Pre_alertDao.getNewcount();
		int sum = 0;
		double avg = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		
		Set<String> setPigID = Pre_alertDao.getPigIdSet(countsum);
		Iterator it = setPigID.iterator();
        while(it.hasNext()){
            String num = (String) it.next();
            
           
            List<Pre_alert> pigList = Pre_alertDao.getPigFrequency(num);
            if(pigList.size()==24){
            	Iterator listIt = pigList.iterator();
            	while(listIt.hasNext()){
            		Pre_alert pig = (Pre_alert) listIt.next();
            		sum = sum+Integer.valueOf(pig.getFrequency());
            	}
            	avg = Double.valueOf(df.format(sum/24.0));
            	
            	int fre = Pre_alertDao.getPigFrequencyByNum(num);
            	if(fre>3*avg){
            		int pigFlagCount = PigDeliveryFlagDao.getCountByNum(num);
            		if(pigFlagCount==0){	
            			PigDeliveryFlagDao.savePigFlag(num);
            		}else if(PigDeliveryFlagDao.getpigFlag(num)==2){
            			if(PigDeliveryFlagDao.getPigIsAlarm(num)==0){
            				System.out.print("该猪尚未发送预警！");
            				System.out.println("发送预警信息，并存储本次已预警信息！");
            				PigDeliveryFlagDao.updatePigIsAlarm(num);
            			}else{
            				System.out.println("该猪已发送预警信息！");
            				continue;
            			}
            		}else{
            			
            			PigDeliveryFlagDao.updatePigFlag(num,2);
            		}
            	}else{
            		
            		PigDeliveryFlagDao.deletePigFlag(num);
            	}
            }else{
            	continue;
            }
        }
		
		

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		main1();
	}

}
