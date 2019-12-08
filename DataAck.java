
import java.io.Serializable;
public class DataAck implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ackNo;

	public int getNo() {
		return ackNo;
	}

	public void setNo(int ackNo) {
		this.ackNo = ackNo;
	}

	@Override
	public String toString() {
		return "AckData [ackNo=" + ackNo + "]";
	}
}
	