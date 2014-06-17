/**
 * stratum-proxy is a proxy supporting the crypto-currency stratum pool mining
 * protocol.
 * Copyright (C) 2014  Stratehm (stratehm@hotmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with multipool-stats-backend. If not, see <http://www.gnu.org/licenses/>.
 */
package strat.mining.stratum.proxy.cli;

import java.io.File;
import java.util.List;

import org.apache.log4j.Level;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.BooleanOptionHandler;
import org.kohsuke.args4j.spi.FileOptionHandler;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

/**
 * Parse and stores the parameters given through command line
 * 
 * @author strat
 * 
 */
public class CommandLineOptions {

	private CmdLineParser parser;

	@Option(name = "-f", aliases = { "--conf-file" }, usage = "Use the given configuration file. If set, all other command line options are discarded (except --version and --help)", handler = FileOptionHandler.class, metaVar = "filePath")
	private File configurationFile;

	@Option(name = "-n", aliases = { "--pool-names" }, usage = "Names of the pools. Space separated. (Default to host)", handler = StringArrayOptionHandler.class, metaVar = "name1 [name2] [name3]...")
	private List<String> poolNames;

	@Option(name = "-h", aliases = { "--pool-hosts" }, usage = "Hosts of the stratum servers (only the host, not the protocol since only stratum+tcp is supported), space separated. If at least one pool is specified, -u and -p has to be specified too.", handler = StringArrayOptionHandler.class, metaVar = "host1 [host2] [host3]...")
	private List<String> poolHosts;

	@Option(name = "-u", aliases = { "--pool-users" }, usage = "User names used to connect to the servers, space separated. If there are more pools than users, the last user will be used for reamining pools.", handler = StringArrayOptionHandler.class, metaVar = "user1 [user2] [user3]...")
	private List<String> poolUsers;

	@Option(name = "-p", aliases = { "--pool-passwords" }, usage = "Passwords used for the users, space separated. If there are more pools than passwords, the last password will be used for reamining pools.", handler = StringArrayOptionHandler.class, metaVar = "pass1 [pass2] [pass3]...")
	private List<String> poolPasswords;

	@Option(name = "--set-extranonce-subscribe", usage = "Enable/Disable the extranonce subscribe request on pool (default to false), space separated.", handler = BooleanArrayOptionHandler.class, metaVar = "boolean1 [boolean2] [boolean3]...")
	private List<Boolean> isExtranonceSubscribeEnabled;

	@Option(name = "--log-directory", usage = "The directory where logs will be written", handler = FileOptionHandler.class, metaVar = "directory")
	private File logDirectory;

	@Option(name = "--log-level", usage = "The level of log: OFF, FATAL, ERROR, WARN, INFO, DEBUG, TRACE. Default is INFO", handler = LogLevelOptionHandler.class, metaVar = "LEVEL")
	private Level logLevel;

	@Option(name = "--stratum-listen-port", usage = "The port number to listen incoming connections. (3333 by default)", metaVar = "portNumber")
	private Integer stratumListeningPort;

	@Option(name = "--stratum-listen-address", usage = "The address to bind to listen incoming connections. (0.0.0.0 by default)", metaVar = "ipAddress")
	private String stratumBindAddress;

	@Option(name = "--number-of-submit", usage = "The number of submit for each share. (Only for debug use)", metaVar = "number")
	private Integer numberOfSubmit;

	@Option(name = "--rest-listen-port", usage = "The port number to listen REST requests. (8888 by default)", metaVar = "portNumber")
	private Integer restListenPort;

	@Option(name = "--rest-listen-address", usage = "The address to bind to listen REST requests. (0.0.0.0 by default)", metaVar = "ipAddress")
	private String restBindAddress;

	@Option(name = "--getwork-listen-port", usage = "The port number to listen Getwork requests. (8332 by default)", metaVar = "portNumber")
	private Integer getworkListenPort;

	@Option(name = "--getwork-listen-address", usage = "The address to bind to listen Getwork requests. (0.0.0.0 by default)", metaVar = "ipAddress")
	private String getworkBindAddress;

	@Option(name = "--version", usage = "Print the version.", handler = BooleanOptionHandler.class)
	private boolean isVersionRequested;

	@Option(name = "--help", usage = "Print this help.", handler = BooleanOptionHandler.class)
	private boolean isHelpRequested;

	@Option(name = "--pool-connection-retry-delay", usage = "Delay in seconds before retry to connect to an inactive pool. (5 seconds by default). 0 to disable retry.")
	private Integer poolConnectionRetryDelay;

	@Option(name = "--pool-reconnect-stability-period", usage = "Delay in seconds before declaring the pool as stable and workers could be moved on this pool. (30 seconds by default). 0 to disable.")
	private Integer poolReconnectStabilityPeriod;

	@Option(name = "--pool-no-notify-timeout", usage = "Delay in seconds to declare a pool as inactive if no mining.notify request received since the last one. (120 seconds by default). 0 to disable.")
	private Integer poolNoNotifyTimeout;

	@Option(name = "--pool-no-reconnect-different-host", usage = "Do not accept client.reconnect if connection on a different host is requested. Still accept reconnection on another port on the same host. If not set, accept all reconnection requests.", handler = BooleanOptionHandler.class)
	private boolean isRejectReconnect;

	@Option(name = "--pool-append-worker-names", usage = "Append the worker name to the username configured for the pool to sumbit shares. (false by default) Use --pool-worker-name-separator to specify the separator to use.", handler = BooleanArrayOptionHandler.class, metaVar = "boolean1 [boolean2] [boolean3]...")
	private List<Boolean> poolsAppendWorkerNames;

	@Option(name = "--pool-worker-name-separator", usage = "Specify the separator to use between the pool username and the worker name. (. by default)", handler = StringArrayOptionHandler.class, metaVar = "separator1 [separator2] [separator3]...")
	private List<String> poolsWorkerNameSeparator;

	@Option(name = "--pool-use-worker-password", usage = "Use the worker password instead of the pool password to authorize workers. (false by default)", handler = BooleanArrayOptionHandler.class, metaVar = "boolean1 [boolean2] [boolean3]...")
	private List<Boolean> poolsUseWorkerPassword;

	@Option(name = "--pool-hashrate-sampling-period", usage = "The sampling period in seconds used to calculate hashrate on pools. (600 seconds by default)")
	private Integer poolHashrateSamplingPeriod;

	@Option(name = "--user-hashrate-sampling-period", usage = "The sampling period in seconds used to calculate hashrate for connected users. (600 seconds by default)")
	private Integer userHashrateSamplingPeriod;

	@Option(name = "--connection-hashrate-sampling-period", usage = "The sampling period in seconds used to calculate hashrate on workers conections. (600 seconds by default)")
	private Integer connectionHashrateSamplingPeriod;

	@Option(name = "--scrypt", usage = "Used to adjust target when mining scrypt coins. Used to estimate hashrate and for getwork workers.", handler = BooleanOptionHandler.class)
	private boolean isScrypt;

	public CommandLineOptions() {
		parser = new CmdLineParser(this);
	}

	public void parseArguments(String... args) throws CmdLineException {
		parser.parseArgument(args);
	}

	public File getLogDirectory() {
		return logDirectory;
	}

	public void printUsage() {
		parser.printUsage(System.out);
	}

	public Integer getStratumListeningPort() {
		return stratumListeningPort;
	}

	public String getStratumBindAddress() {
		return stratumBindAddress;
	}

	public Integer getRestListenPort() {
		return restListenPort;
	}

	public String getRestBindAddress() {
		return restBindAddress;
	}

	public Level getLogLevel() {
		return logLevel;
	}

	public boolean isVersionRequested() {
		return isVersionRequested;
	}

	public boolean isHelpRequested() {
		return isHelpRequested;
	}

	public Integer getPoolConnectionRetryDelay() {
		return poolConnectionRetryDelay;
	}

	public Integer getPoolNoNotifyTimeout() {
		return poolNoNotifyTimeout;
	}

	public Boolean isRejectReconnect() {
		return isRejectReconnect;
	}

	public Integer getUserHashrateSamplingPeriod() {
		return userHashrateSamplingPeriod;
	}

	public Integer getConnectionHashrateSamplingPeriod() {
		return connectionHashrateSamplingPeriod;
	}

	public Integer getGetworkListenPort() {
		return getworkListenPort;
	}

	public String getGetworkBindAddress() {
		return getworkBindAddress;
	}

	public Boolean isScrypt() {
		return isScrypt;
	}

	public File getConfigurationFile() {
		return configurationFile;
	}

	public Integer getNumberOfSubmit() {
		return numberOfSubmit;
	}

	public Integer getPoolReconnectStabilityPeriod() {
		return poolReconnectStabilityPeriod;
	}

	public Integer getPoolHashrateSamplingPeriod() {
		return poolHashrateSamplingPeriod;
	}

	public List<String> getPoolNames() {
		return poolNames;
	}

	public List<String> getPoolHosts() {
		return poolHosts;
	}

	public List<String> getPoolUsers() {
		return poolUsers;
	}

	public List<String> getPoolPasswords() {
		return poolPasswords;
	}

	public List<Boolean> getPoolsAppendWorkerNames() {
		return poolsAppendWorkerNames;
	}

	public List<String> getPoolsWorkerNameSeparator() {
		return poolsWorkerNameSeparator;
	}

	public List<Boolean> getPoolsUseWorkerPassword() {
		return poolsUseWorkerPassword;
	}

	public List<Boolean> getIsExtranonceSubscribeEnabled() {
		return isExtranonceSubscribeEnabled;
	}

}
