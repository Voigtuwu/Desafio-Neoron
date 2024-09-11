import React from "react";
import { HeaderContainer, Logo, Nav, NavLink } from "./styles";
import logoNeoron from "../../assets/images/Logotipo-Neoron.svg";

function Header() {
  return (
    <HeaderContainer>
      <Nav>
        <NavLink href="/">
          <Logo src={logoNeoron} alt="logo" />
        </NavLink>
      </Nav>
      <Nav>
        <NavLink href="/voos">Voos</NavLink>
        <NavLink href="/cadastrar">Cadastrar Voo</NavLink>
        <NavLink href="/atualizar">Atualizar Voo</NavLink>
      </Nav>
    </HeaderContainer>
  );
}

export default Header;
