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
public enum AddressType {

    BITCOIN_PUBKEY(0),
    BITCOIN_TESTNET_PUBKEY(111),
    BITCOIN_SCRIPT(5),
    BITCOIN_TESTNET_SCRIPT(196),
    DOGECOIN_PUBKEY(30),
    DOGECOIN_SCRIPT(22),
    DOGECOIN_TESTNET_PUBKEY(113),
    DOGECOIN_TESTNET_SCRIPT(196),
    DARKCOIN_PUBKEY(76),
    DARKCOIN_SCRIPT(16),
    REDDCOIN_PUBKEY(0x3d),
    REDDCOIN_SCRIPT(0x05),
    /**
     * used for unknown cryptocoin addresses
     */
    UNKNOWN(-1);
    private final int identifier;

    private AddressType(int identifier) {
        this.identifier = identifier;
    }

    /**
     *
     * @return returns the network identifier for bitcoin mainnet with a single
     * signed privkey this would be 1 if its a multisignature 3
     */
    public int getIdentifier() {
        return identifier;
    }

}
