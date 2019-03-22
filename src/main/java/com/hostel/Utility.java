package com.hostel;

import com.hostel.commands.AdmissionCommand;
import com.hostel.domain.FeeStatus;

public class Utility {

	public static boolean isEmpty( String input )
	{
		if( input == null ) return true;
		if( input.trim().length() == 0 ) return true;
		return false;
	}
	
	public static AdmissionCommand updateHostelFeeStatus( AdmissionCommand command, String hostelFee, String acFee )
	{
		double pendingFee = command.isAcRoom() ? Double.parseDouble( hostelFee ) + Double.parseDouble( acFee ) - command.getHostelFeePaid() 
				   : Double.parseDouble( hostelFee ) - command.getHostelFeePaid();

		if( pendingFee <= 0.0 ) {
			command.setHostelFeeStatus( FeeStatus.PAID_IN_FULL );
		}
		else
		{
			command.setHostelFeeStatus( FeeStatus.PARTIAL );
		}
		
		return command;
	}
}
