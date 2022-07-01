package com.chj9.cms.web.compont;

import com.chj9.cms.common.logging.SouthernQuietLogger;
import com.chj9.cms.common.logging.SouthernQuietLoggerFactory;
import com.chj9.cms.common.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final SouthernQuietLogger logger = SouthernQuietLoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);



    @Value("${jwt.header}")
    private String token_header;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	@Autowired
	@Qualifier("myUserDetailsService")
	private UserDetailsService myUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain)
			throws ServletException, IOException {

        String url = request.getRequestURI();
        logger.message("请求的URI").context("uri",url).info();

        String auth_token = request.getHeader(token_header);
        final String auth_token_start = tokenHead;

        if (StringUtils.isNotEmpty(auth_token) && auth_token.startsWith(auth_token_start)) {
            auth_token = auth_token.substring(auth_token_start.length());
            String username = jwtTokenUtil.getUserNameFromToken(auth_token);
            logger.message("Checking authentication for user").context("username",username).debug();

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            	UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(auth_token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.message("setting security context").context("username",username).debug();
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } 
        
		chain.doFilter(request, response);
	}

}
