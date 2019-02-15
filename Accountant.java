import java.util.*;
import java.math.*;
import java.time.*;

class Account {
	//User Details
	final double DEFAULT_BALANCE = 5000.0;
	private String accountName;
	private long accountNumber;
	private double accountBalance;
	private ArrayList<AccountTransaction> transactionList = new ArrayList<>();
	
	//Constructor
	public Account(String accountName) {
		AccountManager manager = new AccountManager();
		this.accountName = accountName;
		accountBalance = DEFAULT_BALANCE;
		accountNumber = manager.generateAccountNumber();
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
	//Get specific List of Transactions Overloaded
	public ArrayList<AccountTransaction> getTransaction() {
		return transactionList;
	}
	public ArrayList<AccountTransaction> getTransaction(Date transactionDate) {
		ArrayList<AccountTransaction> transList = new ArrayList<>();
		for (AccountTransaction transactionToCheck : transactionList) {
			if (transactionToCheck.isTransactionAMatch(transactionDate)) {
				 transList.add(transactionToCheck);
			}
		}
		return transList;
	}
	public ArrayList<AccountTransaction> getTransaction(String transType) {
		ArrayList<AccountTransaction> transList = new ArrayList<>();
		for (AccountTransaction transactionToCheck : transactionList) {
			if (transactionToCheck.isTransactionAMatch(transType)) {
				transList.add(transactionToCheck);
			}
		}
		return transList;
	}
	public ArrayList<AccountTransaction> getTransaction(double amount) {
		ArrayList<AccountTransaction> transList = new ArrayList<>();
		for (AccountTransaction transactionToCheck : transactionList) {
			if (transactionToCheck.isTransactionAMatch(amount)) {
				transList.add(transactionToCheck);
			}
		}
		return transList;
	}
	public ArrayList<AccountTransaction> getTransaction(boolean isInvalid) {
		ArrayList<AccountTransaction> transList = new ArrayList<>();
		for (AccountTransaction transactionToCheck : transactionList) {
			if (transactionToCheck.isTransactionInvalid() == isInvalid) {
				transList.add(transactionToCheck);
			}
		}
		return transList;
	}
}

class AccountManager {
	
	//Create account
	public Account createAccount(String accountName) {
		Account newAccount = new Account(accountName);
		return newAccount;
	}
	
	//Generate account number
	public long generateAccountNumber() {
		return 123456789;
	}
	
	//Debit account
	public void debit(Account accountToDebit, double amount) {
		AccountTransaction newTransaction = new AccountTransaction(amount, "Debit");
		if (accountToDebit.getAccountBalance() - amount > 0) {
			accountToDebit.addTransaction(newTransaction);
			double oldBalance = accountToDebit.getAccountBalance();
			accountToDebit.setAccountBalance(oldBalance - amount);
		}else {
			newTransaction.setInvalidTransaction();
			accountToDebit.addTransaction(newTransaction);
		}
	}
	
	//Credit account
	public void credit(Account accountToCredit, double amount) {
		AccountTransaction newTransaction = new AccountTransaction(amount, "Credit");
		accountToCredit.addTransaction(newTransaction);
		double oldBalance = accountToCredit.getAccountBalance();
		accountToCredit.setAccountBalance(oldBalance + amount);
	}
	
	//Print Account Statement Overloaded
	public void printStatement(Account accountToPrint) {
		AccountHelper helper = new AccountHelper();
		double totalCashFlow = 0.0;
		helper.header();
		for (AccountTransaction transToPrint : accountToPrint.getTransaction()) {
			if (transToPrint.isTransactionInvalid()) {
				transToPrint.printTransaction();
				if (transToPrint.isTransactionAMatch("Debit")) {
					totalCashFlow -= transToPrint.getAmount();
				}else if (transToPrint.isTransactionAMatch("Credit")) {
					totalCashFlow += transToPrint.getAmount();
				}
			}else transToPrint.printInvalidTransaction();
		}
		helper.resultPrinter(totalCashFlow);
	}
	public void printStatement(Account accountToPrint, String transType) {
		AccountHelper helper = new AccountHelper();
		double totalCashFlow = 0.0;
		helper.header();
		for (AccountTransaction transToPrint : accountToPrint.getTransaction(transType)) {
			if (transToPrint.isTransactionInvalid()) {
				if (transType.equals("Debit")) {
					transToPrint.printTransaction();
					totalCashFlow -= transToPrint.getAmount();
				}else if (transType.equals("Credit")) {
					totalCashFlow += transToPrint.getAmount();
				}
			}else transToPrint.printInvalidTransaction();	
		}
		helper.resultPrinter(totalCashFlow);
	}
	public void printStatement(Account accountToPrint, Date transactionDate) {
		AccountHelper helper = new AccountHelper();
		double totalCashFlow = 0.0;
		helper.header();
		for (AccountTransaction transToPrint : accountToPrint.getTransaction(transactionDate)) {
			if (transToPrint.isTransactionInvalid()) {
				if (transToPrint.isTransactionAMatch("Debit")) {
					transToPrint.printTransaction();
					totalCashFlow -= transToPrint.getAmount();
				}else if (transToPrint.isTransactionAMatch("Credit")) {
					totalCashFlow += transToPrint.getAmount();
				}
			}else transToPrint.printInvalidTransaction();		
		}
		helper.resultPrinter(totalCashFlow);
	}
	
	//Generate random transaction
	public void generateRandomTransaction(Account account, int numOfTrans) {
		Random randTrans = new Random();
		
		for (int counter = numOfTrans; counter > 0; counter--) {
			double randAmount = (double) (Math.random()*100000);
			int randTransType = randTrans.nextInt(2);
			if (randTransType == 1) debit(account, randAmount);
			else if (randTransType == 0) credit(account, randAmount);
		}
	}
}

class AccountTransaction {
	AccountHelper helper = new AccountHelper();
	private Date transactionDate = helper.getDate();
	private double amount;
	private String transactionType;
	private boolean isValid = true;
	
	//Constructor
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
	
	//Mutators
	public void setInvalidTransaction() {
		isValid = false;
	}
	
	//Transaction Filter Overloaded
	public boolean isTransactionAMatch(String transactionType) {
		return this.transactionType == transactionType;
	}
	public boolean isTransactionAMatch(double amount) {
		return this.amount == amount;
	}
	public boolean isTransactionAMatch(Date transactionDate) {
		return this.transactionDate == transactionDate;
	}
	public boolean isTransactionInvalid() {
		return isValid;
	}
	
	//Print transaction
	public void printTransaction() {
		helper.tablePrinter(amount, transactionType, transactionDate);
	}
	public void printInvalidTransaction() {
		helper.tablePrinter("Insufficient Funds", transactionType, transactionDate);
	}
}

class AccountHelper {
	
	//Capture and return date
	public Date getDate() {
		LocalDate endDate = LocalDate.now(); //end date
		long end = endDate.toEpochDay();
		Date date = new Date(end);
		return date;
	}
	
	//Print formatted Table (Overloaded)
	public void header() {
		System.out.printf("%15s%15s%-30s%s\n", "Amount", "", "Transaction", "Date");
		System.out.println("-----------------------------------------------------------------------------------------");
	}
	public void tablePrinter(double amount, String transactionType, Date transactionDate) {
		System.out.printf("%15.2f%15s%-30s%s\n", amount, "", transactionType, transactionDate);
	}
	public void tablePrinter(String errorMessage, String transactionType, Date transactionDate) {
		System.out.printf("%-15s%12s%-30s%s\n", errorMessage, "", transactionType, transactionDate);
	}
	public void resultPrinter(double result) {
		System.out.println("---------------------");
		System.out.printf("%15.2f\n", result);
		System.out.println("---------------------");
	}
}

class Accountant {
	public static void main(String[] args) {  //You may express your accountant nature in this section
		
		AccountManager am = new AccountManager();
		
		//Create first account
		Account account_1 = am.createAccount("Young Udochi");
		System.out.printf("\n%s's balance is: %.2f\n",account_1.getAccountName(), account_1.getAccountBalance());
		am.debit(account_1, 550);
		am.credit(account_1, 16500);
		am.generateRandomTransaction(account_1, 10);
		am.printStatement(account_1);
		System.out.printf("%s's new Balance is: %.2f\n",account_1.getAccountName(), account_1.getAccountBalance());
		System.out.println("\n");
		
		//Create Second account
		Account account_2 = am.createAccount("Kamsi Udochi");
		System.out.printf("\n%s's balance is: %.2f\n", account_2.getAccountName(), account_2.getAccountBalance());
		am.credit(account_2, 1500);
		am.debit(account_2, 350);
		am.credit(account_2, 750);
		am.generateRandomTransaction(account_2, 5);
		am.printStatement(account_2);
		System.out.printf("%s's new Balance is: %.2f\n", account_2.getAccountName(), account_2.getAccountBalance());
	}
}