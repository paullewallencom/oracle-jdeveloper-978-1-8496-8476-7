<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces/rich" prefix="af"%>
<f:view>
  <af:document id="d1" title="Test">
    <af:pageTemplate viewId="/WEB-INF/templates/TemplateDef1.jspx" id="pt1">
      <f:facet name="mainContent"/>
      <f:facet name="menuBar">
        <af:menuBar id="mb1">
          <af:menu text="File" id="m1">
            <af:commandMenuItem text="Save" id="cmi1"
                                icon="/images/filesave.png"/>
            <af:commandMenuItem text="Action" id="cmi2"
                                icon="/images/action.png"/>
            <af:commandMenuItem text="Mail" id="cmi3"
                                icon="/images/envelope.png"/>
            <af:commandMenuItem text="Print" id="cmi4"
                                icon="/images/print.png"/>
          </af:menu>
        </af:menuBar>
      </f:facet>
      <f:facet name="topBar">
        <af:group id="g1">
          <af:commandToolbarButton id="ctb1" shortDesc="Save"
                                   icon="/images/filesave.png"/>
          <af:commandToolbarButton id="ctb2" shortDesc="Action"
                                   icon="/images/action.png"/>
          <af:commandToolbarButton id="ctb3" shortDesc="Mail"
                                   icon="/images/envelope.png"/>
          <af:commandToolbarButton id="ctb4" shortDesc="Print"
                                   icon="/images/print.png"/>
        </af:group>
      </f:facet>
      <f:facet name="popupContent"/>
    </af:pageTemplate>
  </af:document>
</f:view>