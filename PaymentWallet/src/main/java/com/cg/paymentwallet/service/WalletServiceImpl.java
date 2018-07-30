package com.cg.paymentwallet.service;

import com.cg.paymentwallet.Exception.IWalletException;
import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dao.IWalletDao;
import com.cg.paymentwallet.dao.WalletDaoImpl;
import com.cg.paymentwallet.dto.Wallet;

public class WalletServiceImpl implements IWalletService {

	IWalletDao dao = new WalletDaoImpl();

	public boolean createAccount(Wallet wallet) {
		boolean result = false;
		dao.createAccount(wallet);
		return result;
	}

	public double showBalance(String userId) {

		return dao.showBalance(userId);
	}

	public boolean deposit(String userId, double amount) {
		boolean result = false;
		if (amount > 0) {
			dao.deposit(userId, amount);
			result = true;
		}

		return result;
	}

	public boolean withdraw(String userId, double amount) {
		boolean result = false;
		if (dao.showBalance(userId) >= amount) {
			dao.withdraw(userId, amount);
			result = true;
		}

		return result;
	}

	public boolean fundTransfer(String userIdSender, String userIdReceiver, double amount) throws WalletException {
		boolean result = false;
		if (dao.showBalance(userIdSender) >= amount) {
			if (dao.fundTransfer(userIdSender, userIdReceiver, amount)) {
				result = true;	
			}
			
		}

		return result;
	}

	public String printTransactions(String userId) {

		return null;
	}

	public boolean validateDetails(Wallet wallet) throws WalletException {
		boolean result = false;
		String regex = "[A-Z]{1}[a-z]+";
		String regex2 = "[0-9]{10}";
		String regex3 = "[a-z0-9_.]{1,}@[a-z]{1,10}.com";
		if (wallet.getName().matches(regex)) {
			if (wallet.getPhNumber().matches(regex2)) {
				if (wallet.getEmailId().matches(regex3)) {
					result = true;

				} else
					throw new WalletException(IWalletException.ERROR3);
			} else
				throw new WalletException(IWalletException.ERROR2);
		} else
			throw new WalletException(IWalletException.ERROR1);
		return result;
	}

	public Wallet login(String id, String password) throws WalletException {

		return dao.login(id, password);
	}

}
