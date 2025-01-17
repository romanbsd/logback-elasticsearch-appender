package com.internetitem.logback.elasticsearch.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import com.internetitem.logback.elasticsearch.config.Property;

public abstract class AbstractPropertyAndEncoder<T> {
	private final Property property;
	private final PatternLayoutBase<T> layout;

	public AbstractPropertyAndEncoder(Property property, Context context) {
		this.property = property;

		this.layout = getLayout();
		this.layout.setContext(context);
		this.layout.setPattern(property.getValue());
		this.layout.setPostCompileProcessor(null);
		this.layout.start();
	}

	protected abstract PatternLayoutBase<T> getLayout();

	public String encode(T event) {
		return layout.doLayout(event);
	}

	public String getName() {
		return property.getName();
	}

	public boolean allowEmpty() {
		return property.isAllowEmpty();
	}

	public Property.Type getType() {
		return property.getType();
	}
}
