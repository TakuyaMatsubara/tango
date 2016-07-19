package jp.co.sbps;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MainForm implements Serializable{
	
	private static final long serialVersionUID = -3201728685446988505L;

	
	private List<RequestForm> requests;
	
}