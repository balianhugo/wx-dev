package dev.gavin.wx.message;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

public class WxMsgProcessor {

	public static WxMsgData textParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setContent(root.elementText("Content"));
		data.setMsgId(root.elementText("MsgId"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));

		return data;
	}

	public static String textXML(WxMsgData msg) {

		Document document = DocumentHelper.createDocument();

		Element nroot = document.addElement("xml");
		nroot.addElement("ToUserName").addCDATA(msg.getToUserName());
		nroot.addElement("FromUserName").addCDATA(msg.getFromUserName());
		nroot.addElement("CreateTime").addText(msg.getCreateTime());
		nroot.addElement("MsgType").addCDATA(WxMessageType.WX_MSG_TEXT);
		nroot.addElement("Content").addCDATA(msg.getContent());

		return document.asXML();
	}

	public static WxMsgData imageParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setMsgId(root.elementText("MsgId"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setPicUrl(root.elementText("PicUrl"));
		data.setMediaId(root.elementText("MediaId"));

		return data;
	}

	public static String imageXML(WxMsgData msg) {

		Document document = DocumentHelper.createDocument();

		Element nroot = document.addElement("xml");
		nroot.addElement("ToUserName").addCDATA(msg.getToUserName());
		nroot.addElement("FromUserName").addCDATA(msg.getFromUserName());
		nroot.addElement("CreateTime").addText(msg.getCreateTime());
		nroot.addElement("MsgType").addCDATA(WxMessageType.WX_MSG_IMAGE);
		nroot.addElement("Image").addElement("MediaId").addCDATA(msg.getMediaId());
		
		return document.asXML();
	}
	
	public static WxMsgData voiceParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setMsgId(root.elementText("MsgId"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setFormat(root.elementText("Format"));
		data.setMediaId(root.elementText("MediaId"));

		return data;
	}

	public static String voiceXML(WxMsgData msg) {

		Document document = DocumentHelper.createDocument();

		Element nroot = document.addElement("xml");
		nroot.addElement("ToUserName").addCDATA(msg.getToUserName());
		nroot.addElement("FromUserName").addCDATA(msg.getFromUserName());
		nroot.addElement("CreateTime").addText(msg.getCreateTime());
		nroot.addElement("MsgType").addCDATA(WxMessageType.WX_MSG_VOICE);
		nroot.addElement("Voice").addElement("MediaId").addCDATA(msg.getMediaId());
		
		return document.asXML();
	}
	
	public static WxMsgData videoParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setMsgId(root.elementText("MsgId"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setThumbMediaId(root.elementText("ThumbMediaId"));
		data.setMediaId(root.elementText("MediaId"));

		return data;
	}

	public static String videoXML(WxMsgData msg) {

		Document document = DocumentHelper.createDocument();

		Element nroot = document.addElement("xml");
		nroot.addElement("ToUserName").addCDATA(msg.getToUserName());
		nroot.addElement("FromUserName").addCDATA(msg.getFromUserName());
		nroot.addElement("CreateTime").addText(msg.getCreateTime());
		nroot.addElement("MsgType").addCDATA(WxMessageType.WX_MSG_VIDEO);
		Element video = nroot.addElement("Video");
		video.addElement("MediaId").addCDATA(msg.getMediaId());
		video.addElement("Title").addCDATA(msg.getTitle());
		video.addElement("Description").addCDATA(msg.getDescription());
		
		return document.asXML();
	}

	
	public static WxMsgData locationParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setMsgId(root.elementText("MsgId"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setLocation_X(root.elementText("Location_X"));
		data.setLocation_Y(root.elementText("Location_Y"));
		data.setScale(root.elementText("Scale"));
		data.setLabel(root.elementText("Label"));

		return data;
	}
	
	public static WxMsgData linkParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setMsgId(root.elementText("MsgId"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setTitle(root.elementText("Title"));
		data.setDescription(root.elementText("Description"));
		data.setUrl(root.elementText("Url"));

		return data;
	}
	
	
	public static String musicXML(WxMsgData msg) {

		Document document = DocumentHelper.createDocument();

		Element nroot = document.addElement("xml");
		nroot.addElement("ToUserName").addCDATA(msg.getToUserName());
		nroot.addElement("FromUserName").addCDATA(msg.getFromUserName());
		nroot.addElement("CreateTime").addText(msg.getCreateTime());
		nroot.addElement("MsgType").addCDATA(WxMessageType.WX_MSG_MUSIC);
		Element music = nroot.addElement("Music");
		music.addElement("ThumbMediaId").addCDATA(msg.getThumbMediaId());
		music.addElement("Title").addCDATA(msg.getTitle());
		music.addElement("Description").addCDATA(msg.getDescription());
		music.addElement("MusicUrl").addCDATA(msg.getMusicURL());
		music.addElement("HQMusicUrl").addCDATA(msg.getHQMusicUrl());
		
		return document.asXML();
	}
	
	
	public static String newsXML(WxMsgData msg, List<WxMsgData> list) {

		Document document = DocumentHelper.createDocument();

		Element nroot = document.addElement("xml");
		nroot.addElement("ToUserName").addCDATA(msg.getToUserName());
		nroot.addElement("FromUserName").addCDATA(msg.getFromUserName());
		nroot.addElement("CreateTime").addText(msg.getCreateTime());
		nroot.addElement("MsgType").addCDATA(WxMessageType.WX_MSG_NEWS);
		nroot.addElement("ArticleCount").addText(String.valueOf(list.size()));
		
		Element articles = nroot.addElement("Articles");
		for(WxMsgData data: list){
			Element item = articles.addElement("item");
			item.addElement("Title").addCDATA(data.getTitle());
			item.addElement("Description").addCDATA(data.getDescription());
			item.addElement("PicUrl").addCDATA(data.getPicUrl());
			item.addElement("Url").addCDATA(data.getUrl());
		}
		
		
		return document.asXML();
	}
	
	
	//用户订阅与取消订阅
	public static WxMsgData eventSubscribeParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setEvent(root.elementText("Event"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));

		return data;
	}
	
	//用户扫二维码事件
	public static WxMsgData eventScanParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setEvent(root.elementText("Event"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setEventKey(root.elementText("EventKey"));
		data.setTicket(root.elementText("Ticket"));

		return data;
	}
	
	
	public static WxMsgData eventLocationParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setEvent(root.elementText("Event"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setLatitude(root.elementText("Latitude"));
		data.setLongitude(root.elementText("Longitude"));
		data.setPrecision(root.elementText("Precision"));

		return data;
	}

	public static WxMsgData eventClickParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setMsgType(root.elementText("MsgType"));
		data.setEvent(root.elementText("Event"));
		data.setEventKey(root.elementText("EventKey"));

		return data;
	}

	public static WxMsgData eventViewParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setMsgType(root.elementText("MsgType"));
		data.setEvent(root.elementText("Event"));
		data.setEventKey(root.elementText("EventKey"));

		return data;
	}

	public static WxMsgData eventTemplateParse(Document doc) {

		Element root = doc.getRootElement();

		WxMsgData data = new WxMsgData();
		data.setToUserName(root.elementText("ToUserName"));
		data.setFromUserName(root.elementText("FromUserName"));
		data.setEvent(root.elementText("Event"));
		data.setMsgType(root.elementText("MsgType"));
		data.setCreateTime(root.elementText("CreateTime"));
		data.setMsgID(root.elementText("MsgID"));
		data.setStatus(root.elementText("Status"));

		return data;
	}

}