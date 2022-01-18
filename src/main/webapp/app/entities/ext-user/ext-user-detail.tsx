import { IExtUser } from 'app/shared/model/ext-user.model';
import React from 'react';
import { Link, Params } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { getEntity } from './ext-user.reducer';

interface IExtUserDetailProps {
  params: Readonly<Params<string>>;
}

interface IExtUserDetailState {
  loading: boolean;
  extUserEntity: IExtUser;
}

export default class ExtUserDetail extends React.Component<IExtUserDetailProps, IExtUserDetailState> {
  constructor(props: IExtUserDetailProps) {
    super(props);
    this.state = {
      loading: false,
      extUserEntity: {}
    };
  }

  componentDidMount() {
    const { id } = this.props.params;
    this.loadDetail(parseInt(id as string));
  }

  loadDetail(id: number) {
    this.setState({loading: true});
    getEntity(id)
      .then(res => {
        this.setState({ extUserEntity: res.data });
      })
      .catch(err => console.error('Failed to get the list of transaction accounts', err))
      .finally(() => this.setState({loading: false}));
  }

  render() {
    return (
      <Row>
        <Col md="8">
          <h2 data-cy="extUserDetailsHeading">Detail Member</h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{this.state.extUserEntity.name}</dd>
            <dt>
              <span id="firstName">First Name</span>
            </dt>
            <dd>{this.state.extUserEntity.firstName}</dd>
            <dt>
              <span id="lastName">Last Name</span>
            </dt>
            <dd>{this.state.extUserEntity.lastName}</dd>
            <dt>
              <span id="birthDate">Birth Date</span>
            </dt>
            <dd>
              {this.state.extUserEntity.birthDate}
            </dd>
            <dt>
              <span id="address">Address</span>
            </dt>
            <dd>{this.state.extUserEntity.address}</dd>
          </dl>
          <Button tag={Link} to=".." replace color="info" data-cy="entityDetailsBackButton">
            <span className="d-none d-md-inline">Back</span>
          </Button>
        </Col>
      </Row>
    );
  }
}
