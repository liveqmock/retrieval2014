package com.sxjun.retrieval.controller.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sxjun.retrieval.controller.index.NormalImageIndex1Impl;

import frame.retrieval.engine.facade.DBIndexOperatorFacade;
import frame.retrieval.engine.facade.ICreateIndexAllItem;
import frame.retrieval.engine.facade.NormalIndexOperatorFacade;

public class NormalImageIndexJob1 implements Job {
	private ICreateIndexAllItem iciai= null;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		iciai = new NormalImageIndex1Impl();
		NormalIndexOperatorFacade imageIndexAll = new NormalIndexOperatorFacade(iciai);
		imageIndexAll.indexAll();
	}
	
}
