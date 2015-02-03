/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.silabsoft.cryptocoin.identifier.AddressIdentifier;
import org.silabsoft.cryptocoin.identifier.AddressIdentifierException;
import org.silabsoft.cryptocoin.model.Address;

/**
 * Used to validate cryptocoin address strings and attempt to identify CoinType
 * @author Silabsoft
 */
public class IdentificationTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String addressesToTest[] = new String[]{
            "1vF5XepSWqvjMUdkFGQdgE1oqLjhZWoq", //failed size
            "1vF5XepSWqvjMUdkFGQdgE1oqLjhZWoqF", // should be valid
            "DNy1UBfm3QJmgGoVJnaXPtdfKyvdpFwRkc", //dogecoin
            "RqdoFVWu4Keio22GQD9nBSAHMynTNhy39j", //reddcoin
            "Xe32GbzrVi2L4WvFLT6Bf6gtRp1mVgR8rW", //darkcoin
        };
        AddressIdentifier identifier = new AddressIdentifier();
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
