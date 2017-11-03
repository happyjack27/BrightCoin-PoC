package validation;

import java.util.*;
import java.util.Map.Entry;

import serialization.*;

public class TxValidator {
	//how to auto-accept money - without having to sign...?  and keep ledger intact?
	//send must include a strictly increasing timestamp and sequence and chain
	//sender can simply acknowledge reciepts before they send.  
	//  the money is subtracted from the sender when they sign, but not credited to the depositor until they acknowledge.
	//  receiver can also decline, in which case the money goes back to sender on the decline date.  meanwhile coins are in limbo.
	// the receipt transaction number is then chosen when signed by reciever.
	// how to ack/return a decline? 
	
	//there's a send sequence, and a total sequence.  the earliest sent coin is used up (confirmed) first.
	
	//bitcoin rules:
	//https://www.cryptocompare.com/coins/guides/how-does-a-bitcoin-node-verify-a-transaction/
	
	//internal: structure, math, signatures, approvals (needed signatures must say ok), all
	//chained: chronology, inter-tx sigs, inter-tx math, all
	//recurse: 
	//other: accounts
	
	//get signatures function, then confirm all, and approval states

	/*when requesting data:
	number of copies
	nubmer of replications
	number of trusted store copies
	number of trusted store replications 
	*/



}
