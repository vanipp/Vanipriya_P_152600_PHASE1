package com.cg.paymentwallet.dao;

import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dto.Wallet;

public interface IWalletDao {
	public void createAccount(Wallet wallet);

	public double showBalance(String userId);

	public void deposit(String userId, double amount);

	public void withdraw(String userId, double amount);

	public boolean fundTransfer(String userIdSender, String userIdReceiver, double amount) throws WalletException;

	public String printTransactions(String userId);

	public Wallet login(String id, String password) throws WalletException;
}
