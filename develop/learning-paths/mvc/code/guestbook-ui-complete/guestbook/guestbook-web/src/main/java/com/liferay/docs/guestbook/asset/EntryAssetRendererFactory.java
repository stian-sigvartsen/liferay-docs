package com.liferay.docs.guestbook.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.EntryModel;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalService;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.docs.guestbook.service.permission.EntryPermission;
import com.liferay.docs.guestbook.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + "com_liferay_docs_guestbook_portlet_GuestbookPortlet"
}, service = AssetRendererFactory.class)
public class EntryAssetRendererFactory extends 
BaseAssetRendererFactory<Entry> {
public EntryAssetRendererFactory() {
setClassName(CLASS_NAME);
setLinkable(_LINKABLE);
setPortletId("com_liferay_docs_guestbook_portlet_GuestbookPortlet");
setSearchable(true);
}
@Override
public AssetRenderer<Entry> getAssetRenderer(long classPK, int type)
        throws PortalException {
        Entry entry = _entryLocalService.getEntry(classPK);

EntryAssetRenderer entryAssetRenderer =
	new EntryAssetRenderer(entry);

entryAssetRenderer.setAssetRendererType(type);
        return entryAssetRenderer;
}

@Override
public String getClassName() {
        return CLASS_NAME;
}

@Override
public String getIconCssClass() {
    return "pencil";
}

@Override
public String getType() {
        return TYPE;
}

@Override
public boolean hasPermission(
                PermissionChecker permissionChecker, long classPK, String actionId)
        throws Exception {

        Entry entry = _entryLocalService.getEntry(classPK);
		return EntryPermission.contains(permissionChecker, classPK, actionId);
}

@Override
public PortletURL getURLAdd(
LiferayPortletRequest liferayPortletRequest,
LiferayPortletResponse liferayPortletResponse, long classTypeId) {
PortletURL portletURL = null;

try {
  ThemeDisplay themeDisplay =
	  (ThemeDisplay)liferayPortletRequest.getAttribute(
	  	WebKeys.THEME_DISPLAY);

	  portletURL = liferayPortletResponse.createLiferayPortletURL(
	  getControlPanelPlid(themeDisplay),
	  "com_liferay_docs_guestbook_portlet_GuestbookPortlet", PortletRequest.RENDER_PHASE);
  portletURL.setParameter("mvcRenderCommandName", "/html/guestbookmvcportlet/edit_entry");
  portletURL.setParameter("showback", Boolean.FALSE.toString());
}
catch (PortalException e) {
}

return portletURL;
}

@Override
public boolean isLinkable() {
        return _LINKABLE;
}

@Reference(target = "(osgi.web.symbolicname=guestbook.web)")
private ServletContext _servletContext;
@Reference
private EntryLocalService _entryLocalService;
private static final boolean _LINKABLE = true;
public static final String CLASS_NAME = Entry.class.getName();
public static final String TYPE = "entry";

}