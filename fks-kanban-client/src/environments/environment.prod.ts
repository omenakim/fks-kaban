export const environment = {
  production: true,
  baseURL: 'http://localhost:8080',
  tokenWhitelistedDomains: [ new RegExp('localhost:8080') ],
  tokenBlacklistedRoutes: [ new RegExp('\/oauth\/token') ],
  pageSize: 25
};