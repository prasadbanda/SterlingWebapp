package com.applications.webapp.sterling.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.applications.webapp.sterling.service.ContractService;
import com.applications.webapp.sterling.vo.CobrokerVO;
import com.opensymphony.xwork2.ActionSupport;

public class CoBrokerAction extends ActionSupport implements
		SessionAware {

	private String updateStatus;
	private Map<String, Object> session;
	private ContractService contractService;
	// properties for Edit Or Add
	
	private String contractNumber = null;
	private String cobroker = null;
	private String cobrokerPercent = null;
	private String collects = null;
	private String cobrokerNumber = null;
	private String cobrokerDataId = null;
	private String oper;
	// properties for Edit Or Add
	private String id;
	private List<CobrokerVO> cobrokerVOs;
	private Map<String, String> cobrokers;
	private Logger logger = Logger.getLogger(CoBrokerAction.class);

	public String addOrEditCoBrokerData() {
		////System.out.println("collects in cobroker :"+collects);
		if(collects!=null && (collects.equalsIgnoreCase("Yes")||collects.equalsIgnoreCase("on")||collects.equalsIgnoreCase("1")))
				{
					collects="1";
				}
		else
		{
			collects="0";
		}
		CobrokerVO cobrokerVO = new CobrokerVO(cobroker, cobrokerPercent,collects,cobrokerNumber);
 
		if (oper.equalsIgnoreCase("add")) {
			cobrokerVO.setContractNumber(new Integer(contractNumber));
			logger.debug("Updating Cobroker Information");
			boolean status = getContractService().addCoBrokerVo(cobrokerVO);
			if (status)
				setUpdateStatus("Successfully Insert the Record");
			else
				setUpdateStatus("Failed to Insert");
		} else if (oper.equalsIgnoreCase("edit")) {
			cobrokerVO.setContractNumber(new Integer(contractNumber));
			cobrokerVO.setCobrokerDataId(new Integer(cobrokerDataId));
			logger.debug("Updating Cobroker Information");
			boolean status = getContractService().updateCoBrokerVo(cobrokerVO);
			if (status)
				setUpdateStatus("Sucessfully Updated the Record");
			else
				setUpdateStatus("Failed to Update");
		}else if(oper.equalsIgnoreCase("del")){
			logger.debug("Deletting Cobroker");
			getContractService().deleteCobrokerData(new Integer(getId()), new Integer(contractNumber));
		}
		return "success";
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String coBrokersJson() {
		logger.debug("getCoBrokersJson");
		setCobrokerVOs(getContractService().getCoBrokers(
				new Integer(getContractNumber())));
		return "json_success";
	}


	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public String getCobroker() {
		return cobroker;
	}

	public void setCobroker(String cobroker) {
		this.cobroker = cobroker;
	}

	public String getCobrokerPercent() {
		return cobrokerPercent;
	}

	public void setCobrokerPercent(String cobrokerPercent) {
		this.cobrokerPercent = cobrokerPercent;
	}

	public String getCollects() {
		return collects;
	}

	public void setCollects(String collects) {
		this.collects = collects;
	}

	public String getCobrokerNumber() {
		return cobrokerNumber;
	}

	public void setCobrokerNumber(String cobrokerNumber) {
		this.cobrokerNumber = cobrokerNumber;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public List<CobrokerVO> getCobrokerVOs() {
		return cobrokerVOs;
	}

	public void setCobrokerVOs(List<CobrokerVO> cobrokerVOs) {
		this.cobrokerVOs = cobrokerVOs;
	}

	public String getCobrokerDataId() {
		return cobrokerDataId;
	}

	public void setCobrokerDataId(String cobrokerDataId) {
		this.cobrokerDataId = cobrokerDataId;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Map<String, String> getCobrokers() {
		return cobrokers;
	}

	public void setCobrokers(Map<String, String> cobrokers) {
		this.cobrokers = cobrokers;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

}
