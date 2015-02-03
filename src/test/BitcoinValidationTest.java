/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.silabsoft.cryptocoin.identifier.AddressIdentifier;
import org.silabsoft.cryptocoin.identifier.AddressIdentifierException;
import org.silabsoft.cryptocoin.model.Address;
import org.silabsoft.cryptocoin.model.AddressType;

/**
 * Validates only mainnet bitcoin addresses
 * @author Silabsoft
 */
public class BitcoinValidationTest {



    /**
     *
     * @param args
     */
    
    public static void main(String[] args) {
        String addressesToTest[] = new String[]{
            "1vF5XepSWqvjMUdkFGQdgE1oqLjhZWoq", //failed size
            "1vF5XepSWqvjMUdkFGQdgE1oqLjhZWoqF", // should be valid
            "DNy1UBfm3QJmgGoVJnaXPtdfKyvdpFwRkc" //dogecoin
        };
        AddressIdentifier identifier = new AddressIdentifier(new AddressType[]{AddressType.BITCOIN_PUBKEY, AddressType.BITCOIN_SCRIPT});
        for (String address : addressesToTest) {
      
            try {
                Address cca = identifier.identifierAddressFromEncodedString(address);
                System.out.println(cca.getEncodedAddress() + " Type: " + cca.getCryptoCoinTypeAddressType());
            } catch (AddressIdentifierException ex) {
                System.out.println("Address: " + address + " is Invalid: " + ex.getMessage());
            }

        }
    }
}
