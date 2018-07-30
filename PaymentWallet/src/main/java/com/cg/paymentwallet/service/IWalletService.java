package com.cg.paymentwallet.service;

import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dto.Wallet;

public interface IWalletService {

	public boolean createAccount(Wallet wallet);

	public double showBalance(String phNumber);

	public boolean deposit(String phNumber, double amount);

	public boolean withdraw(String phNumber, double amount);

	public boolean fundTransfer(String phSender, String phReceiver, double amount) throws WalletException;

	public String printTransactions(String phNumber);

	public boolean validateDetails(Wallet wallet) throws WalletException;

	public Wallet login(String id, String password) throws WalletException;
}
