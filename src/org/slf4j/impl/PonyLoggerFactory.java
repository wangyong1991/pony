package org.slf4j.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import pony.log.PonyLoggerAdapter;
import pony.log.PonyLogger;

public class PonyLoggerFactory implements ILoggerFactory {

	// key: name (String), value: a PonyLoggerAdapter;
	ConcurrentMap<String, Logger> loggerMap;

	public PonyLoggerFactory() {
		loggerMap = new ConcurrentHashMap<String, Logger>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String)
	 */
	public Logger getLogger(final String name) {
		Logger slf4jLogger = loggerMap.get(name);
		if (slf4jLogger != null) {
			return slf4jLogger;
		} else {
			PonyLogger ponyLogger = new PonyLogger(name);

			Logger newInstance = new PonyLoggerAdapter(ponyLogger);
			Logger oldInstance = loggerMap.putIfAbsent(name, newInstance);
			return oldInstance == null ? newInstance : oldInstance;
		}
	}
}
