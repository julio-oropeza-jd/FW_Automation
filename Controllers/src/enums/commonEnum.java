package enums;

public class commonEnum {
	public enum pageTitles {
		XFormDashBoard {
			public String toString() {
				return "XFORM | Janeiro Digital";
			}
		},
	}
	public enum pagesURLs {
		XFormDashBoardUrl {
			public String toString() {
				return "https://xform-stage.janeirodigital.com/dashboard";
			}
		},
	}
	public enum bodyTitles {
		XFormDashBoardTitle {
			public String toString() {
				return "DASHBOARD";
			}
		},
	}

	public enum xFormMenuTitles {
		XFormDashBoardMenuUsrMngmtTitle {
			public String toString() {
				return "User Management";
			}
		},
		XFormDashBoardMenuTenantTitle {
			public String toString() {
				return "Tenants";
			}
		},
		FormDashBoardMenuSysAdmTitle {
			public String toString() {
				return "System Administration";
			}

		}
	}
	public enum xFormDataProviders {
		XFormDPUserManagement {
			public String toString() {
				return "UserManagement";
			}
		},
		XFormDPTenantsManagement {
			public String toString() {
				return "TenantsManagement";
			}
		},
		XFormDPSystemAdministrationManagement {
			public String toString() {
				return "SystemAdministrationManagement";
			}

		}
	}
}
