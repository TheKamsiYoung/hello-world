import java.util.*;
import java.math.*;
import java.time.*;

class Account {
	//User Details
	private String accountName;
	private long accountNumber;
	private double accountBalance;
	private ArrayList<AccountTransaction> transactionList = new ArrayList<>();
	
	//Constructor
	public Account() {}
	public Account(String accountName) {
		this.accountName = accountName;
	}
	public Account(String accountName, double accountBalance) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
	
	//Mutator methods
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public void addTransaction(AccountTransaction newTransaction) {
		transactionList.add(newTransaction);
	}
	
	//Accessor Methods
	public String getAccountName() {
		return accountName;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public ArrayList<AccountTransaction> getTransaction() {
		return transactionList;
	}
	public ArrayList<AccountTransaction> getTransaction(Date transactionDate) {
		ArrayList<AccountTransaction> transList = new ArrayList<>();
		for (AccountTransaction transaction : transactionList) {
			if (transaction.checkTransaction(transactionDate)) {
				 transList.add(transaction);
			}
		}
		return transList;
	}
	public ArrayList<AccountTransaction> getTransaction(String transType) {
		ArrayList<AccountTransaction> transList = new ArrayList<>();
		for (AccountTransaction transaction : transactionList) {
			if (transaction.checkTransaction(transType)) {
				transList.add(transaction);
			}
		}
		return transList;
	}
	public ArrayList<AccountTransaction> getTransaction(double amount) {
		ArrayList<AccountTransaction> transList = new ArrayList<>();
		for (AccountTransaction transaction : transactionList) {
			if (transaction.checkTransaction(amount)) {
				transList.add(transaction);
			}
		}
		return transList;
	}
	
}

class AccountManager {
	AccountHelper helper = new AccountHelper();
	
	//Generate account number
	public long generateAccountNum() {
		long accountNumber = helper.getAccountNumber();
		return accountNumber;
	}
	
	//Debit account
	public void debit(Account accountToDebit, double amount) {
		AccountTransaction newTransaction = new AccountTransaction(amount, "debit");
		accountToDebit.addTransaction(newTransaction);
		double oldBalance = accountToDebit.getAccountBalance();
		accountToDebit.setAccountBalance(oldBalance - amount);
	}
	
	//Credit account
	public void credit(Account accountToCredit, double amount) {
		AccountTransaction newTransaction = new AccountTransaction(amount, "credit");
		accountToCredit.addTransaction(newTransaction);
		double oldBalance = accountToCredit.getAccountBalance();
		accountToCredit.setAccountBalance(oldBalance + amount);
	}
	
	//Print Statement
	public void printStatement(Account accountToPrint) {
		System.out.println("s/n        Transaction        Time");
		System.out.println("----------------------------------------------------------");
		for (AccountTransaction transToPrint : accountToPrint.getTransaction()) {
			transToPrint.printTransaction();
		}
	}
	public void printStatement(Account accountToPrint, String transType) {
		System.out.println("s/n        Transaction        Time");
		System.out.println("----------------------------------------------------------");
		for (AccountTransaction transToPrint : accountToPrint.getTransaction(transType)) {
			transToPrint.printTransaction(transType);
		}
	}
	public void printStatement(Account accountToPrint, Date transactionDate) {
		System.out.println("s/n        Transaction        Time");
		System.out.println("----------------------------------------------------------");
		for (AccountTransaction transToPrint : accountToPrint.getTransaction(transactionDate)) {
			transToPrint.printTransaction(transactionDate);
		}
	}
}

class AccountTransaction {
	AccountHelper helper1 = new AccountHelper();
	
	private Date transactionDate = helper1.getDate();
	private double amount;
	private String transactionType;
	
	//Constructor
	public AccountTransaction() {}
	public AccountTransaction(double amount, String transactionType) {
		this.amount = amount;
		this.transactionType = transactionType;
	}
	//Accessors
	public Date getTransactionDate() {
		return transactionDate;
	}
	public double getAmount() {
		return amount;
	}
	
	//Transaction Filter
	public boolean checkTransaction(String transactionType) {
		boolean isMatch = false;
		if (this.transactionType.equals(transactionType)) {
			isMatch = true;
		}
		return isMatch;
	}
	public boolean checkTransaction(double amount) {
		boolean isMatch = false;
		if (this.amount == amount) {
			isMatch = true;
		}
		return isMatch;
	}
	public boolean checkTransaction(Date transactionDate) {
		boolean isMatch = false;
		if (this.transactionDate == transactionDate) {
			isMatch = true;
		}
		return isMatch;
	}
	
	//Print transaction
	public void printTransaction() {
			System.out.println(amount + "         " + transactionType + "         " + transactionDate);
	}
	public void printTransaction(String transactionType) {
		if (this.transactionType.equals(transactionType)) {
			System.out.println(amount + "         " + transactionType + "         " + transactionDate);
		}
	}
	public void printTransaction(Date transactionDate) {
		if (this.transactionDate.equals(transactionDate)) {
			System.out.println(amount + "         " + transactionType + "         " + transactionDate);
		}
	}
}

class AccountHelper {
	public Date getDate() {
		LocalDate endDate = LocalDate.now(); //end date
		long end = endDate.toEpochDay();
		Date date = new Date(end);
		return date;
	}
	
	public long getAccountNumber() {
		return 123456789;
	}
}

class Accountant {
	public static void main(String[] args) {
		AccountManager am = new AccountManager();
		Account account_1 = new Account("Kamsi Udochi", 15000);
		Account account_2 = new Account("Young Udochi", 25000);
		
		am.debit(account_1, 550);
		am.credit(account_1, 16500);
		am.credit(account_2, 1500);
		am.debit(account_2, 350);
		am.credit(account_2, 750);
		
		am.printStatement(account_1);
		System.out.println("\n");
		am.printStatement(account_2, "credit" );
		
	}
}