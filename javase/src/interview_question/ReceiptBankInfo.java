package interview_question;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                              //get，set
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class ReceiptBankInfo implements Serializable {
	private String receiptName; // 收款人姓名
	private String bankName; // 收款银行
	private String receiptAccountNumber; // 收款账号
}
