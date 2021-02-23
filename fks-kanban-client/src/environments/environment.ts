export const environment = {
  production: false,
  baseURL: 'http://localhost:8080',
  tokenWhitelistedDomains: [ new RegExp('localhost:8080') ],
  tokenBlacklistedRoutes: [ new RegExp('\/oauth\/token') ],
  pageSize: 25
};

