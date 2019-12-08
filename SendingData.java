import java.io.Serializable;
public class SendingData implements Serializable,Comparable<SendingData> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int seqNum;
	private int checkSum;
	private char payLoad;

	boolean last = false;

	
	@Override
	public String toString() {
		return "SegmentData [seqNum=" + seqNum + ", checkSum=" + checkSum + ", payLoad=" + payLoad + "]";
	}

	public int getSeq() {
		return seqNum;
	}

	public void setSeq(int seqNum) {
		this.seqNum = seqNum;
	}

	public int getErrorCheck() {
		return checkSum;
	}

	public void setErrorCheck(int checkSum) {
		this.checkSum = checkSum;
	}

	public char getData() {
		return payLoad;
	}

	public void setData(char payLoad) {
		this.payLoad = payLoad;
	}

	public boolean isFinal() {
		return last;
	}

	public void setFinal(boolean last) {
		this.last = last;
	}

	@Override
	public int compareTo(SendingData o) {
		
		return this.getSeq()-(o.getSeq());
	}

}
