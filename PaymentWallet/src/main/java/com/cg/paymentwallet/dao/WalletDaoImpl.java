package com.cg.paymentwallet.dao;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.cg.paymentwallet.Exception.IWalletException;
import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dto.Wallet;

public class WalletDaoImpl implements IWalletDao {
	private static HashMap<String, Wallet> map = null;

	static {
		map = new HashMap<String, Wallet>();
	}

	public void createAccount(Wallet wallet) {
		map.put(wallet.getUserId(), wallet);

	}

	public double showBalance(String userId) {
		return map.get(userId).getBalance();
	}

	public void deposit(String userId, double amount) {
		map.get(userId).setBalance(map.get(userId).getBalance() + amount);
		LocalDateTime date=LocalDateTime.now();
		map.get(userId).setTransaction("Rupees "+amount+" Deposited at "+date);
	}

	public void withdraw(String userId, double amount) {
		map.get(userId).setBalance(map.get(userId).getBalance() - amount);
		LocalDateTime date=LocalDateTime.now();
		map.get(userId).setTransaction("Rupees "+amount+" Withdrawn at "+date);
	}

	public boolean fundTransfer(String userIdSender, String userIdReceiver, double amount) throws WalletException {
		boolean result = false;
if (map.containsKey(userIdReceiver)) {
	map.get(userIdSender).setBalance(map.get(userIdSender).getBalance()-amount);
	map.get(userIdReceiver).setBalance(map.get(userIdReceiver).getBalance()+amount);
	LocalDateTime date=LocalDateTime.now();
	map.get(userIdSender).setTransaction("Rupees "+amount+" Transfered to "+userIdReceiver +" at  "+date);
	map.get(userIdReceiver).setTransaction("Rupees "+amount+" Received from "+userIdSender +" at   "+date);
	result=true;
}else throw new WalletException(IWalletException.ERROR6);
		return result;
	}

	public String printTransactions(String userId) {

		return null;
	}

	public Wallet login(String id, String password) throws WalletException {
		Wallet wall = null;
		if (map.containsKey(id) && map.get(id).getPassword().equals(password)) {
			wall = map.get(id);
		} else
			throw new WalletException(IWalletException.ERROR5);
		return wall;
	}

}
