package com.example.demo.filter;

import com.example.demo.service.JwtSevice;
import com.example.demo.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtSevice jwtSevice;

    @Autowired
    private UserService userDetailSevice;

    public JwtFilter() {
    }
    //bất kì api nào dc gọi tới đều phải đi qua hàm này
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //token được gửi qua authorization do bên react đặt tên nêu 1 request bth không có authorization tức là chưa đăng nhập
        String authHeader = request.getHeader("authorization");
        String token=null;
        String username=null;
        if(authHeader!=null&&authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            username=jwtSevice.extractUsername(token);
        }

        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userDetailSevice.loadUserByUsername(username);
            if(jwtSevice.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}