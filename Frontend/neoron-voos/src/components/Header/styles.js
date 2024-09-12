import styled from "styled-components";

export const HeaderContainer = styled.header`
    background-color: #F5F5F5;
    padding: 15px 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #DDD;
`;

export const Logo = styled.img`
    height: 40px;
`;

export const Nav = styled.nav`
    display: flex;
    gap: 25px;
`;

export const NavLink = styled.a`
    color: black;
    text-decoration: none;
    font-size: 16px;
    transition: color 0.3 ease, text-decoration 0.3 ease;

    &:hover {
        text-decoration: underline;
        color: orange;
    }
`;