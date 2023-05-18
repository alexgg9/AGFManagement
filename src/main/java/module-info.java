module ManagementFights {
	exports AGFPromotions.ManagementFights;
	exports AGFPromotions.ManagementFights.model.enums;
	exports model.connection;
	exports AGFPromotions.ManagementFights.test;
	exports AGFPromotions.ManagementFights.utils;
	exports AGFPromotions.ManagementFights.model.domain;
	exports AGFPromotions.ManagementFights.model.DAO;

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