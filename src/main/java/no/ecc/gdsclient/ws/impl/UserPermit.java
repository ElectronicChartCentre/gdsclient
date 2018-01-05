package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 */
public class UserPermit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _userId;
	private String _name;
	private String _userPermitString;
	private String _comments;
	private boolean _backupSystem;

	public UserPermit() {
	}

	public void setUserPermitString(String ups) {
		_userPermitString = ups;
	}

	public String getUserPermitString() {
		return _userPermitString;
	}

	public void setName(String n) {
		_name = n;
	}

	public String getName() {
		return _name;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	public String getUserId() {
		return _userId;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public boolean getIsBackupSystem() {
		return _backupSystem;
	}

	public void setIsBackupSystem(boolean b) {
		_backupSystem = b;
	}

 }
