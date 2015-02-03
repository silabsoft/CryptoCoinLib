/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.cryptocoin.model;

/**
 *
 * @author Silabsoft
 */
public class Address {


    private final byte[] decodedPayload;
    private final String encodedAddress;
    private final byte[] checksum;
    private final AddressType cryptoCoinTypeAddressType;

    /**
     *
     * @param decodedPayload - decoded base58 address
     * @param encodedAddress - encoded address example: 1vF5XepSWqvjMUdkFGQdgE1oqLjhZWoq
     * @param checksum - checksum of address
     * @param cryptoCoinTypeAddressType - cryptocoin type example: BITCOIN
     */
    public Address(byte[] decodedPayload, String encodedAddress, byte[] checksum, AddressType cryptoCoinTypeAddressType) {
        this.decodedPayload = decodedPayload;
        this.encodedAddress = encodedAddress;
        this.checksum = checksum;
        this.cryptoCoinTypeAddressType = cryptoCoinTypeAddressType;
    }

    /**
     * 
     * @return the address base58 decoded in a byte array
     */
    public byte[] getDecodedPayload() {
        return decodedPayload;
    }

    /**
     *
     * @return the base58 encoded address
     */
    public String getEncodedAddress() {
        return encodedAddress;
    }

    /**
     *
     * @return checksum of address in byte array
     */
    public byte[] getChecksum() {
        return checksum;
    }

    /**
     *
     * @return the cryptocoin type
     */
    public AddressType getCryptoCoinTypeAddressType() {
        return cryptoCoinTypeAddressType;
    }

    /**
     *
     * @return returns the decoded address in a hex string format
     */
    public String getDecodedAddressHexString() {
        return bytesToHex(decodedPayload);
    }
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Method source:  http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
     * @param bytes
     * @return bytes to hex string
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    @Override
    public String toString() {
        return "CryptoCoinAddress{" + "encodedAddress=" + encodedAddress + ", cryptoCoinTypeAddressType=" + cryptoCoinTypeAddressType + '}';
    }

    
}
