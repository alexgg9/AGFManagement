module ManagementFights {
	exports AGFPromotions.ManagementFights;
	exports model.enums;
	exports model.connection;
	exports test;
	exports utils;
	exports model.domain;
	exports model.DAO;

	requires java.sql;
	requires java.xml;
	requires java.xml.bind;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	
	opens model.connection to java.xml.bind;
	opens AGFPromotions.ManagementFights to javafx.fxml;
}