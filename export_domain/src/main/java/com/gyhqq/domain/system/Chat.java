package com.gyhqq.domain.system;

import com.gyhqq.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Chat extends BaseEntity implements Serializable {

    private String chatId;

    private String sendId;

    private String receiveId;

    private String content;

    private Date sendTime;

    private String companyId;

    private String companyName;

    private String subject;

    private String sendName;

    private String receiveName;

    private String sendDept;

    private String receiveDept;

    private Integer state;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId == null ? null : chatId.trim();
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId == null ? null : sendId.trim();
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId == null ? null : receiveId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_chat.subject
     *
     * @return the value of pe_chat.subject
     * @mbg.generated
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_chat.subject
     *
     * @param subject the value for pe_chat.subject
     * @mbg.generated
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_chat.send_name
     *
     * @return the value of pe_chat.send_name
     * @mbg.generated
     */
    public String getSendName() {
        return sendName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_chat.send_name
     *
     * @param sendName the value for pe_chat.send_name
     * @mbg.generated
     */
    public void setSendName(String sendName) {
        this.sendName = sendName == null ? null : sendName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_chat.receive_name
     *
     * @return the value of pe_chat.receive_name
     * @mbg.generated
     */
    public String getReceiveName() {
        return receiveName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_chat.receive_name
     *
     * @param receiveName the value for pe_chat.receive_name
     * @mbg.generated
     */
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_chat.send_dept
     *
     * @return the value of pe_chat.send_dept
     * @mbg.generated
     */
    public String getSendDept() {
        return sendDept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_chat.send_dept
     *
     * @param sendDept the value for pe_chat.send_dept
     * @mbg.generated
     */
    public void setSendDept(String sendDept) {
        this.sendDept = sendDept == null ? null : sendDept.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_chat.receive_dept
     *
     * @return the value of pe_chat.receive_dept
     * @mbg.generated
     */
    public String getReceiveDept() {
        return receiveDept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_chat.receive_dept
     *
     * @param receiveDept the value for pe_chat.receive_dept
     * @mbg.generated
     */
    public void setReceiveDept(String receiveDept) {
        this.receiveDept = receiveDept == null ? null : receiveDept.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pe_chat.state
     *
     * @return the value of pe_chat.state
     * @mbg.generated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pe_chat.state
     *
     * @param state the value for pe_chat.state
     * @mbg.generated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId='" + chatId + '\'' +
                ", sendId='" + sendId + '\'' +
                ", receiveId='" + receiveId + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", subject='" + subject + '\'' +
                ", sendName='" + sendName + '\'' +
                ", receiveName='" + receiveName + '\'' +
                ", sendDept='" + sendDept + '\'' +
                ", receiveDept='" + receiveDept + '\'' +
                ", state=" + state +
                '}';
    }
}