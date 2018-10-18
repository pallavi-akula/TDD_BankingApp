package com.capgemini.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountTest {
AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;

	@Before
	public void setUp() throws Exception {
      MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepository);
	}
	@Test(expected=com.capgemini.exceptions.InsufficientOpeningBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientOpeningBalanceException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientOpeningBalanceException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	

    /*@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
    public void whenTheDepositAmountReturnsInvalidAccountNumberException() throws InvalidAccountNumberException
    {
        accountService.depositAmount(101,300);
    }*/
    
    @Test
    public void whenThedepositIsSuccessfully() throws InvalidAccountNumberException
    {
        Account account =new Account();
        account.setAccountNumber(101);
        account.setAmount(5000);
        when(accountRepository.save(account)).thenReturn(true);
        assertEquals(account, accountService.depositAmount(101, 500));
    }
    
    
    @Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
    public void whenTheWithDrawAmountReturnsInsufficientBalanceException() throws InvalidAccountNumberException, InsufficientBalanceException
    {
        accountService.withdrawAmount(101,6000);
    }
    
    
    /*@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
    public void whenTheWithDrawAmountReturnsInvalidAccountException() throws InvalidAccountNumberException, InsufficientBalanceException
    {
        accountService.withdrawAmount(101,6000);
    }*/
    
    
    @Test
    public void whenThedepositIsReturnSuccessfully() throws InvalidAccountNumberException, InsufficientBalanceException
    {
        Account account =new Account();
        account.setAccountNumber(101);
        account.setAmount(5000);
        when(accountRepository.save(account)).thenReturn(true);
        assertEquals(account, accountService.withdrawAmount(101, 500));
    }
    
    
    
	

}
