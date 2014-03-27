package com.gdcable.epm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gdcable.epm.publics.Constants;
/**
 * 付款记录明细实体
 * <pre>
 * </pre>
 * @author 华军
 * @version 1.0, 2013-9-25
 */
@Entity
@Table(name = "PM_PAYMENT",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_PAYMENT", sequenceName = "PM_PAYMENT_SEQ", allocationSize = 1)
public class ProPayment implements Serializable
{
	private Long id;     //付款单号
}
