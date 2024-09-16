import styled from "styled-components";

export const HeaderContainer = styled.header`
    background-color: #F5F5F5;
    padding: 15px 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #DDD;

    /* Responsividade */
    @media (max-width: 768px) {
        padding: 15px 20px;
    }

    @media (max-width: 480px) {
        padding: 10px 10px;
        flex-direction: column;
        align-items: flex-start;
    }
`;

export const Logo = styled.img`
    height: 40px;

    /* Responsividade */
    @media (max-width: 768px) {
        height: 35px;
    }

    @media (max-width: 480px) {
        height: 30px;
    }
`;

export const Nav = styled.nav`
    display: flex;
    gap: 25px;

    /* Responsividade */
    @media (max-width: 768px) {
        gap: 15px;
    }

    @media (max-width: 480px) {
        gap: 10px;
        flex-direction: column;
        align-items: flex-start;
    }
`;

export const NavLink = styled.a`
    color: black;
    text-decoration: none;
    font-size: 16px;
    transition: color 0.3s ease, text-decoration 0.3s ease;

    &:hover {
        text-decoration: underline;
        color: orange;
    }

    /* Responsividade */
    @media (max-width: 768px) {
        font-size: 14px;
    }

    @media (max-width: 480px) {
        font-size: 12px;
    }
`;
