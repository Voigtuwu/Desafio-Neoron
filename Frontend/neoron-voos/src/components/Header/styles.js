import styled from "styled-components";

export const HeaderContainer = styled.header`
    background-color: lightgray;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
`

export const Logo = styled.img`
    height: 30px;
`

export const Nav = styled.nav`
    display: flex;
    gap: 15px;
    justify-content: space-between;
`

export const NavLink = styled.a`
    color: black;
    text-decoration: none;

    &:hover {
        text-decoration: underline;
        color: blue;
    }
`