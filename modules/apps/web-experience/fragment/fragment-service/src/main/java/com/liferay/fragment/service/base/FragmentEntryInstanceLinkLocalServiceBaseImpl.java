/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.fragment.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.fragment.model.FragmentEntryInstanceLink;
import com.liferay.fragment.service.FragmentEntryInstanceLinkLocalService;
import com.liferay.fragment.service.persistence.FragmentEntryInstanceLinkPersistence;
import com.liferay.fragment.service.persistence.FragmentEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the fragment entry instance link local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.fragment.service.impl.FragmentEntryInstanceLinkLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.fragment.service.impl.FragmentEntryInstanceLinkLocalServiceImpl
 * @see com.liferay.fragment.service.FragmentEntryInstanceLinkLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class FragmentEntryInstanceLinkLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements FragmentEntryInstanceLinkLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.fragment.service.FragmentEntryInstanceLinkLocalServiceUtil} to access the fragment entry instance link local service.
	 */

	/**
	 * Adds the fragment entry instance link to the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentEntryInstanceLink the fragment entry instance link
	 * @return the fragment entry instance link that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentEntryInstanceLink addFragmentEntryInstanceLink(
		FragmentEntryInstanceLink fragmentEntryInstanceLink) {
		fragmentEntryInstanceLink.setNew(true);

		return fragmentEntryInstanceLinkPersistence.update(fragmentEntryInstanceLink);
	}

	/**
	 * Creates a new fragment entry instance link with the primary key. Does not add the fragment entry instance link to the database.
	 *
	 * @param fragmentEntryInstanceLinkId the primary key for the new fragment entry instance link
	 * @return the new fragment entry instance link
	 */
	@Override
	public FragmentEntryInstanceLink createFragmentEntryInstanceLink(
		long fragmentEntryInstanceLinkId) {
		return fragmentEntryInstanceLinkPersistence.create(fragmentEntryInstanceLinkId);
	}

	/**
	 * Deletes the fragment entry instance link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentEntryInstanceLinkId the primary key of the fragment entry instance link
	 * @return the fragment entry instance link that was removed
	 * @throws PortalException if a fragment entry instance link with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FragmentEntryInstanceLink deleteFragmentEntryInstanceLink(
		long fragmentEntryInstanceLinkId) throws PortalException {
		return fragmentEntryInstanceLinkPersistence.remove(fragmentEntryInstanceLinkId);
	}

	/**
	 * Deletes the fragment entry instance link from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentEntryInstanceLink the fragment entry instance link
	 * @return the fragment entry instance link that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FragmentEntryInstanceLink deleteFragmentEntryInstanceLink(
		FragmentEntryInstanceLink fragmentEntryInstanceLink) {
		return fragmentEntryInstanceLinkPersistence.remove(fragmentEntryInstanceLink);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(FragmentEntryInstanceLink.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return fragmentEntryInstanceLinkPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentEntryInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return fragmentEntryInstanceLinkPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentEntryInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return fragmentEntryInstanceLinkPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return fragmentEntryInstanceLinkPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return fragmentEntryInstanceLinkPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public FragmentEntryInstanceLink fetchFragmentEntryInstanceLink(
		long fragmentEntryInstanceLinkId) {
		return fragmentEntryInstanceLinkPersistence.fetchByPrimaryKey(fragmentEntryInstanceLinkId);
	}

	/**
	 * Returns the fragment entry instance link with the primary key.
	 *
	 * @param fragmentEntryInstanceLinkId the primary key of the fragment entry instance link
	 * @return the fragment entry instance link
	 * @throws PortalException if a fragment entry instance link with the primary key could not be found
	 */
	@Override
	public FragmentEntryInstanceLink getFragmentEntryInstanceLink(
		long fragmentEntryInstanceLinkId) throws PortalException {
		return fragmentEntryInstanceLinkPersistence.findByPrimaryKey(fragmentEntryInstanceLinkId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(fragmentEntryInstanceLinkLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FragmentEntryInstanceLink.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"fragmentEntryInstanceLinkId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(fragmentEntryInstanceLinkLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(FragmentEntryInstanceLink.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"fragmentEntryInstanceLinkId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(fragmentEntryInstanceLinkLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FragmentEntryInstanceLink.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"fragmentEntryInstanceLinkId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return fragmentEntryInstanceLinkLocalService.deleteFragmentEntryInstanceLink((FragmentEntryInstanceLink)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return fragmentEntryInstanceLinkPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the fragment entry instance links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.fragment.model.impl.FragmentEntryInstanceLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment entry instance links
	 * @param end the upper bound of the range of fragment entry instance links (not inclusive)
	 * @return the range of fragment entry instance links
	 */
	@Override
	public List<FragmentEntryInstanceLink> getFragmentEntryInstanceLinks(
		int start, int end) {
		return fragmentEntryInstanceLinkPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of fragment entry instance links.
	 *
	 * @return the number of fragment entry instance links
	 */
	@Override
	public int getFragmentEntryInstanceLinksCount() {
		return fragmentEntryInstanceLinkPersistence.countAll();
	}

	/**
	 * Updates the fragment entry instance link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentEntryInstanceLink the fragment entry instance link
	 * @return the fragment entry instance link that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FragmentEntryInstanceLink updateFragmentEntryInstanceLink(
		FragmentEntryInstanceLink fragmentEntryInstanceLink) {
		return fragmentEntryInstanceLinkPersistence.update(fragmentEntryInstanceLink);
	}

	/**
	 * Returns the fragment entry instance link local service.
	 *
	 * @return the fragment entry instance link local service
	 */
	public FragmentEntryInstanceLinkLocalService getFragmentEntryInstanceLinkLocalService() {
		return fragmentEntryInstanceLinkLocalService;
	}

	/**
	 * Sets the fragment entry instance link local service.
	 *
	 * @param fragmentEntryInstanceLinkLocalService the fragment entry instance link local service
	 */
	public void setFragmentEntryInstanceLinkLocalService(
		FragmentEntryInstanceLinkLocalService fragmentEntryInstanceLinkLocalService) {
		this.fragmentEntryInstanceLinkLocalService = fragmentEntryInstanceLinkLocalService;
	}

	/**
	 * Returns the fragment entry instance link persistence.
	 *
	 * @return the fragment entry instance link persistence
	 */
	public FragmentEntryInstanceLinkPersistence getFragmentEntryInstanceLinkPersistence() {
		return fragmentEntryInstanceLinkPersistence;
	}

	/**
	 * Sets the fragment entry instance link persistence.
	 *
	 * @param fragmentEntryInstanceLinkPersistence the fragment entry instance link persistence
	 */
	public void setFragmentEntryInstanceLinkPersistence(
		FragmentEntryInstanceLinkPersistence fragmentEntryInstanceLinkPersistence) {
		this.fragmentEntryInstanceLinkPersistence = fragmentEntryInstanceLinkPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the fragment entry local service.
	 *
	 * @return the fragment entry local service
	 */
	public com.liferay.fragment.service.FragmentEntryLocalService getFragmentEntryLocalService() {
		return fragmentEntryLocalService;
	}

	/**
	 * Sets the fragment entry local service.
	 *
	 * @param fragmentEntryLocalService the fragment entry local service
	 */
	public void setFragmentEntryLocalService(
		com.liferay.fragment.service.FragmentEntryLocalService fragmentEntryLocalService) {
		this.fragmentEntryLocalService = fragmentEntryLocalService;
	}

	/**
	 * Returns the fragment entry persistence.
	 *
	 * @return the fragment entry persistence
	 */
	public FragmentEntryPersistence getFragmentEntryPersistence() {
		return fragmentEntryPersistence;
	}

	/**
	 * Sets the fragment entry persistence.
	 *
	 * @param fragmentEntryPersistence the fragment entry persistence
	 */
	public void setFragmentEntryPersistence(
		FragmentEntryPersistence fragmentEntryPersistence) {
		this.fragmentEntryPersistence = fragmentEntryPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.fragment.model.FragmentEntryInstanceLink",
			fragmentEntryInstanceLinkLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.fragment.model.FragmentEntryInstanceLink");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FragmentEntryInstanceLinkLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return FragmentEntryInstanceLink.class;
	}

	protected String getModelClassName() {
		return FragmentEntryInstanceLink.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = fragmentEntryInstanceLinkPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = FragmentEntryInstanceLinkLocalService.class)
	protected FragmentEntryInstanceLinkLocalService fragmentEntryInstanceLinkLocalService;
	@BeanReference(type = FragmentEntryInstanceLinkPersistence.class)
	protected FragmentEntryInstanceLinkPersistence fragmentEntryInstanceLinkPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.fragment.service.FragmentEntryLocalService.class)
	protected com.liferay.fragment.service.FragmentEntryLocalService fragmentEntryLocalService;
	@BeanReference(type = FragmentEntryPersistence.class)
	protected FragmentEntryPersistence fragmentEntryPersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}