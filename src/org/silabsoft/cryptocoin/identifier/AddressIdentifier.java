/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.cryptocoin.identifier;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.silabsoft.cryptocoin.model.Address;
import org.silabsoft.cryptocoin.model.AddressType;
import com.github.vrotaru.Base58;

/**
 *
 * @author Silabsoft
 */
public class AddressIdentifier {

    private static final int BASE58_DECODE_LENGTH = 25;
    private static final int ADDRESS_TYPE_IDENTIFIER_LENGTH = 2;
    private static final int CHECKSUM_OFFSET = 21;
    private static final int CHECKSUM_LENGTH = 4;
    private final AddressType[] requiredAddressType;

    /**
     * Used for simply validating the address and attempting to calculate what the address type is. Not extremely accurate considering multiple Cryptocoins use the same identifier.
     */
    public AddressIdentifier() {
        this.requiredAddressType = null;
    }

    /**
     * Used for validating a single addressType for example if you wanted to validate only Bitcoin Addresses you can with this method.
     * @param addressType
     * 
     */
    public AddressIdentifier(AddressType addressType) {
        this(new AddressType[]{addressType});
    }

    /**
     * Used for validating multiple addressTypes.
     * @param at
     */
    public AddressIdentifier(AddressType[] at) {
        this.requiredAddressType = at;
    }

    /**
     *
     * @param address
     * @return address object if address is valid
     * @throws AddressIdentifierException thrown when address cannot be identified
     */
    public Address identifierAddressFromEncodedString(String address) throws AddressIdentifierException {
        byte[] payload = Base58.decode(address);
        int length = payload.length;
        if (length != BASE58_DECODE_LENGTH) {
            throw new AddressIdentifierException("Invalid Decode Length");
        }
        byte[] checksum = new byte[4];
        byte[] body = new byte[21];
        byte[] type = new byte[2];
        for (int i = 0; i < BASE58_DECODE_LENGTH; i++) {
            if (i < ADDRESS_TYPE_IDENTIFIER_LENGTH) {
                type[i] = payload[i];
            }
            if (i < CHECKSUM_OFFSET) {

                body[i] = payload[i];
            } else {
                checksum[i - CHECKSUM_OFFSET] = payload[i];
            }
        }

        byte[] cc = new byte[4];
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            System.arraycopy(md.digest(md.digest(body)), 0, cc, 0, 4);

        } catch (NoSuchAlgorithmException ex) {
            throw new AddressIdentifierException(ex);
        }
        if (compareChecksum(cc, checksum)) {
            if (requiredAddressType == null) { //do not check for address type instead guestimate which type is being identified
                return new Address(payload, address, checksum, getAddressType(type[0]));
            }
            return new Address(payload, address, checksum, checkRequiredAddressType(type[0]));
        }
        throw new AddressIdentifierException("Checksum Mismatch");

    }

    /**
     * compares 2 byte array values
     * @param a 
     * @param b
     * @return true if checksums are equal
     */
    public boolean compareChecksum(byte[] a, byte[] b) {
        if (a.length != b.length || a.length != CHECKSUM_LENGTH || b.length != CHECKSUM_LENGTH) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    private AddressType getAddressType(byte identifier) {
        for (AddressType t : AddressType.values()) {
            if (t.getIdentifier() == identifier) {
                return t;
            }
        }
        return AddressType.UNKNOWN;
    }

    private AddressType checkRequiredAddressType(byte identifier) throws AddressIdentifierException {
        for (AddressType t :requiredAddressType) {
            if (t.getIdentifier() == identifier) {
                return t;
            }
        }
        throw new AddressIdentifierException("Invalid CryptoCoin Identifier");
    }

}
