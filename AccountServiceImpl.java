package com.capgemini.service;

import java.util.LinkedList;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepository;

public class AccountServiceImpl implements AccountService{
	LinkedList<Account> accounts=new LinkedList<>();
    
AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account createAccount (int accountNumber,int amount) throws InsufficientOpeningBalanceException
	{
		int a,b,c;
		if(amount<500)
		{
			throw new InsufficientOpeningBalanceException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	
	private Account searchAccount(int accountNumber)throws InvalidAccountNumberException
    {
        
    //  Object accounts;
        for(Account account : accounts)
        {
            if(account.getAccountNumber()==accountNumber)
            {
                return account;
            }
        }
        throw new InvalidAccountNumberException();
        
    }
    

    public Account withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException, InsufficientBalanceException
    {
        Account account=searchAccount(accountNumber);
        
        
        if((account.getAmount()-amount)<=0)
        {
            throw new InsufficientBalanceException();
        }
        account.setAmount(account.getAmount()-amount);
        //return account.getAmount();
        
        
        if(accountRepository.save(account))
        {
            return account;
        }
         
        return null;
        
    }
        
    public Account depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException
    {
        Account account=searchAccount(accountNumber);
        
        
        if(amount>=0)
        {
            account.setAmount(account.getAmount()+amount);
            //return account.getAmount();

            throw new InvalidAccountNumberException();

        }
        if(accountRepository.save(account))
        {
            return account;
        }
         
        return null;
        
        
    }

	
}
