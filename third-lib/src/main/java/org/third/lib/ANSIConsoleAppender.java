package org.third.lib;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;


/**
 * Color based Appender for log4j. Still not fully working See HOR-1054 Wrapper don't allow to change stdout
 */
public class ANSIConsoleAppender extends ConsoleAppender
{
	private static final int NORMAL = 0;
	private static final int BRIGHT = 1;

	private static final int FOREGROUND_RED = 31;
	private static final int FOREGROUND_GREEN = 32;
	private static final int FOREGROUND_YELLOW = 33;
	private static final int FOREGROUND_BLUE = 34;
	private static final int FOREGROUND_CYAN = 36;

	private static final String PREFIX = "\u001b[";

	private static final String SUFFIX = "m";
	private static final char SEPARATOR = ';';
	private static final String END_COLOR = PREFIX + SUFFIX;

	private String fatalColor = PREFIX + BRIGHT + SEPARATOR + FOREGROUND_RED + SUFFIX;
	private String errorColor = PREFIX + NORMAL + SEPARATOR + FOREGROUND_RED + SUFFIX;
	private String warnColor = PREFIX + NORMAL + SEPARATOR + FOREGROUND_YELLOW + SUFFIX;
	private String infoColor = PREFIX + NORMAL + SEPARATOR + FOREGROUND_GREEN + SUFFIX;
	private String debugColor = PREFIX + NORMAL + SEPARATOR + FOREGROUND_CYAN + SUFFIX;
	private String traceColor = PREFIX + NORMAL + SEPARATOR + FOREGROUND_BLUE + SUFFIX;


	private final boolean enabled;

	public ANSIConsoleAppender()
	{
		enabled = !System.getProperty("os.name").startsWith("Windows") || "true".equalsIgnoreCase(System.getProperty("forceANSI"));
	}

	@Override
	public void setLayout(final Layout delegate)
	{
		// Wrapping the layout by one which is injecting ANSI color sequences.
		// The advantage of this is to get complete strings that are written in one go
		// to the underlying writer!
		super.setLayout(new Layout()
		{
			@Override
			public void activateOptions()
			{
				delegate.activateOptions(); // delegate
			}

			@Override
			public boolean ignoresThrowable()
			{
				return delegate.ignoresThrowable(); // delegate
			}

			@Override
			public String format(final LoggingEvent event)
			{
				String ret = delegate.format(event);
				if (enabled) // append colors if applicable
				{
					ret = getColor(event.getLevel()) + ret + END_COLOR;
				}
				return ret;
			}
		});
	}

	public void setFatalColor(final String fatalColor)
	{
		this.fatalColor = PREFIX + fatalColor + SUFFIX;
	}

	public void setErrorColor(final String errorColor)
	{
		this.errorColor = PREFIX + errorColor + SUFFIX;
	}

	public void setWarnColor(final String warnColor)
	{
		this.warnColor = PREFIX + warnColor + SUFFIX;
	}

	public void setInfoColor(final String infoColor)
	{
		this.infoColor = PREFIX + infoColor + SUFFIX;
	}

	public void setDebugColor(final String debugColor)
	{
		this.debugColor = PREFIX + debugColor + SUFFIX;
	}

	public void setTraceColor(final String traceColor)
	{
		this.traceColor = PREFIX + traceColor + SUFFIX;
	}

	/**
	 * Get the appropriate control characters to change the color for the specified logging level.
	 * 
	 * @return A String of ANSI control characters that will set the console text to the appropriate color for the given
	 *         log level.
	 */
	private String getColor(final Level level)
	{
		switch (level.toInt())
		{
			case Priority.FATAL_INT:
				return fatalColor;
			case Priority.ERROR_INT:
				return errorColor;
			case Priority.WARN_INT:
				return warnColor;
			case Priority.INFO_INT:
				return infoColor;
			case Priority.DEBUG_INT:
				return debugColor;
			default:
				return traceColor;
		}
	}
}
