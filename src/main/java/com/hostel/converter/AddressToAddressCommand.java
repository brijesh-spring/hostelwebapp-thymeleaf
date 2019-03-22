package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.AddressCommand;
import com.hostel.domain.Address;

@Component
public class AddressToAddressCommand implements Converter<Address , AddressCommand>{

	@Override
	public AddressCommand convert(Address source) {
		
		AddressCommand result = new AddressCommand();
		result.setAddressLine1( source.getAddressLine1() );
		result.setAddressLine2( source.getAddressLine2() );
		result.setCity( source.getCity() );
		result.setCounty( source.getCounty() );
		result.setHouseNo( source.getHouseNo() );
		result.setId( source.getId() );
		result.setLandmark( source.getLandmark() );
		result.setPlotNo( source.getPlotNo() );
		result.setSocietyName( source.getSocietyName() );
		result.setState( source.getState() );
		result.setType( source.getType() );
		result.setZipcode( source.getZipcode() );
		
		return result;
	}

}
