// Serve index.html for paths like /blog/2026-09-21 when running locally, the same way the
// production host does (see wrangler.jsonc).
config.devServer = config.devServer || {};
config.devServer.historyApiFallback = true;
