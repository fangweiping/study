package interview_question;

import java.io.Serializable;
import lombok.Data;

@Data
public class ReceiptApply implements Serializable {
	private Integer billCode; // 订单号
	private String receiptName; // 收款人姓名
	private String bankName; // 收款银行
	private String receiptAccountNumber; // 收款账号
	private Integer money; // 收款金额
	private Byte isDeleted;
}


